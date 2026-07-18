#!/usr/bin/env python3
"""Enhance 09-interview-source-map-300.md with indexes and referenced-file appendix."""

from __future__ import annotations

import re
from collections import defaultdict
from pathlib import Path

ROOT = Path("/Users/lidong/Documents/Project2")
SRC = ROOT / "sp2-springboot/docs/handbook/09-interview-source-map-300.md"
OUT = SRC

FORBIDDEN_ZH = [
    "你的",
    "我的",
    "我們",
    "建議使用",
    "採用最新",
    "最佳實務",
    "身為工程師",
    "值得注意的是",
]


def github_heading_slug(heading: str) -> str:
    """Approximate GitHub / VS Code markdown heading slug."""
    s = heading.strip().lower()
    s = s.replace("—", "").replace("–", "")
    s = re.sub(r"[^\w\s\-]", "", s, flags=re.UNICODE)
    s = re.sub(r"\s+", "-", s.strip())
    s = re.sub(r"-{2,}", "-", s)
    return s


def file_anchor(path: str) -> str:
    slug = path.replace("/", "-").replace(".", "-").replace(" ", "-")
    slug = re.sub(r"-{2,}", "-", slug)
    return f"file-{slug}"


def parse_questions(text: str) -> tuple[str, list[dict], str]:
    """Return (preamble_before_q001, questions, trailing_after_last_q)."""
    m = re.search(r"^## Q001 —", text, re.M)
    if not m:
        raise SystemExit("Q001 not found")
    pre = text[: m.start()]
    rest = text[m.start() :]
    blocks = re.split(r"(?=^## Q\d{3} —)", rest, flags=re.M)
    questions = []
    for b in blocks:
        if not b.startswith("## Q"):
            continue
        hm = re.match(r"^## (Q\d{3}) — (.+?)\n", b)
        qid, title = hm.group(1), hm.group(2).strip()
        cat = re.search(r"### Category\n\n(.+)", b).group(1).strip()
        diff = re.search(r"### Difficulty\n\n(.+)", b).group(1).strip()
        files_m = re.search(r"### Verified source files\n\n(.*?)(?=\n### )", b, re.S)
        files = []
        for line in files_m.group(1).splitlines():
            line = line.strip()
            if line.startswith("- "):
                p = line[2:].strip()
                if p and not p.lower().startswith("documentation"):
                    files.append(p)
        # strip trailing separators / orphan anchors if re-processing an enhanced file
        body = re.sub(r"(?:\n<a id=\"Q\d{3}\"></a>\s*|\n+---\s*)+\Z", "", b)
        body = body.strip() + "\n"
        # drop leading HTML anchor if present
        body = re.sub(r"^<a id=\"Q\d{3}\"></a>\s*\n+", "", body)
        vm = re.search(r"(### Verification status\n\n- .+)\n?", body)
        if vm:
            body = body[: vm.end()].rstrip() + "\n"
        questions.append(
            {
                "id": qid,
                "title": title,
                "cat": cat,
                "diff": diff,
                "files": files,
                "body": body,
                "slug": github_heading_slug(f"{qid} {title}"),
            }
        )
    # Drop anything after Q300 body that is not part of questions (none expected)
    return pre, questions, ""


def layer_from_path(path: str) -> str:
    if "/domain/" in path:
        return "domain"
    if "/application/" in path:
        return "application"
    if "/infrastructure/" in path:
        return "infrastructure"
    if "/presentation/" in path:
        return "presentation"
    if "/security/" in path:
        return "security"
    if "/common/" in path:
        return "common"
    if "/test/" in path:
        return "test"
    if path.endswith(".sql") or "/db/migration" in path:
        return "migration"
    if path.endswith(".yml") or path.endswith(".yaml") or path.endswith(".properties"):
        return "configuration"
    if "/docs/" in path or path.endswith(".md"):
        return "documentation"
    if "/.github/" in path:
        return "ci"
    if "Dockerfile" in path or "docker-compose" in path:
        return "container"
    if path.endswith(".tf") or path.endswith(".tfstate"):
        return "terraform"
    return "other"


def analyze_java(content: str, path: str) -> dict:
    pkg = re.search(r"^package\s+([\w.]+);", content, re.M)
    pkg = pkg.group(1) if pkg else ""
    type_m = re.search(
        r"^(?:public\s+|protected\s+|private\s+)?(?:abstract\s+|final\s+)?"
        r"(class|interface|enum|record)\s+(\w+)",
        content,
        re.M,
    )
    kind = type_m.group(1) if type_m else "type"
    name = type_m.group(2) if type_m else Path(path).stem
    annos = sorted(set(re.findall(r"^@(\w+)", content, re.M)))
    methods = re.findall(
        r"^\s*(?:public|protected|private)\s+(?:static\s+)?(?:[\w.<>,\[\]\s]+)\s+(\w+)\s*\(",
        content,
        re.M,
    )
    # filter constructors and common noise
    methods = [m for m in methods if m != name and m not in ("if", "for", "while", "switch")]
    methods = list(dict.fromkeys(methods))[:18]
    imports = re.findall(r"^import\s+(?:static\s+)?([\w.]+);", content, re.M)
    deps = []
    for imp in imports:
        if imp.startswith("com.tlbank.lending"):
            deps.append(imp)
        elif imp.startswith("org.springframework"):
            deps.append(imp)
        elif imp.startswith("jakarta."):
            deps.append(imp)
    deps = list(dict.fromkeys(deps))[:12]
    javadoc = ""
    jd = re.search(r"/\*\*\s*(.*?)\s*\*/", content, re.S)
    if jd:
        lines = [
            re.sub(r"^\s*\*\s?", "", ln).strip()
            for ln in jd.group(1).splitlines()
            if not ln.strip().startswith("@")
        ]
        javadoc = " ".join(x for x in lines if x)[:320]
    return {
        "kind": kind,
        "name": name,
        "package": pkg,
        "annotations": annos[:15],
        "methods": methods,
        "deps": deps,
        "javadoc": javadoc,
        "layer": layer_from_path(path),
    }


def analyze_yaml(content: str) -> dict:
    keys = re.findall(r"^([A-Za-z0-9_.-]+):", content, re.M)
    top = list(dict.fromkeys(keys))[:25]
    jobs: list[str] = []
    jobs_m = re.search(r"(?ms)^jobs:\s*\n(.*)\Z", content)
    if jobs_m:
        jobs = re.findall(r"^  ([a-z][a-z0-9_-]*):\s*$", jobs_m.group(1), re.M)
    triggers: list[str] = []
    on_m = re.search(r"(?ms)^on:\s*\n(.*?)(?=^[a-zA-Z])", content)
    if on_m:
        triggers = re.findall(r"^  ([a-z_]+)\s*:", on_m.group(1), re.M)
    name = re.search(r"^name:\s*(.+)$", content, re.M)
    return {
        "top_keys": top,
        "jobs": jobs[:20],
        "triggers": triggers,
        "workflow_name": name.group(1).strip() if name else "",
        "has_on": bool(on_m),
        "excerpt": content[:500],
    }


def analyze_sql(content: str, path: str) -> dict:
    ver = re.search(r"V(\d+)__", Path(path).name)
    stmts = re.findall(
        r"\b(CREATE TABLE|ALTER TABLE|CREATE INDEX|INSERT INTO|DROP |UPDATE )\s+([`\"\w.]+)?",
        content,
        re.I,
    )
    return {
        "version": ver.group(1) if ver else "",
        "statements": [(a.strip(), (b or "").strip()) for a, b in stmts[:20]],
        "excerpt": content[:400],
    }


def analyze_tf(content: str) -> dict:
    providers = re.findall(r'provider\s+"([^"]+)"', content)
    resources = re.findall(r'resource\s+"([^"]+)"\s+"([^"]+)"', content)
    variables = re.findall(r'variable\s+"([^"]+)"', content)
    outputs = re.findall(r'output\s+"([^"]+)"', content)
    return {
        "providers": providers,
        "resources": resources,
        "variables": variables,
        "outputs": outputs,
        "excerpt": content[:500],
    }


def analyze_md(content: str) -> dict:
    title = ""
    for line in content.splitlines():
        if line.startswith("# "):
            title = line[2:].strip()
            break
    paras = [p.strip() for p in re.split(r"\n\s*\n", content) if p.strip() and not p.strip().startswith("#")]
    summary = paras[0][:400] if paras else content[:400]
    summary = re.sub(r"\[([^\]]+)\]\([^)]+\)", r"\1", summary)
    return {"title": title, "summary": summary}


def analyze_docker(content: str) -> dict:
    froms = re.findall(r"^FROM\s+(\S+)", content, re.M)
    stages = re.findall(r"^FROM\s+\S+(?:\s+AS\s+(\w+))?", content, re.M | re.I)
    users = re.findall(r"^USER\s+(\S+)", content, re.M)
    expos = re.findall(r"^EXPOSE\s+(\S+)", content, re.M)
    health = "HEALTHCHECK" in content
    return {
        "froms": froms,
        "stages": [s for s in stages if s],
        "users": users,
        "expose": expos,
        "healthcheck": health,
        "excerpt": content[:500],
    }


def business_tags_for_question(q: dict) -> list[str]:
    title = q["title"].lower()
    cat = q["cat"]
    tags = []
    mapping = [
        (["otp", "OTP"], "OTP verification"),
        (["idempoten"], "Idempotent application create"),
        (["review", "credit review", "ReviewCase"], "Credit review workflow"),
        (["notif", "sms", "email", "Notification"], "Notifications (mock)"),
        (["report", "excel", "pdf"], "Reporting"),
        (["upload", "document", "storage", "multipart"], "Document upload / storage"),
        (["login", "session", "authent", "Security", "authorize", "ROLE_"], "Authentication & authorization"),
        (["audit", "Auditable", "AOP"], "Audit logging"),
        (["cache", "CacheStore", "evict"], "Product / parameter cache"),
        (["schedul", "cron"], "Scheduled jobs"),
        (["flyway", "migration", "V1"], "Schema migration"),
        (["docker", "compose", "container"], "Local container runtime"),
        (["github", "workflow", "ci/cd", "deploy"], "CI/CD pipeline"),
        (["terraform", "iac"], "Local Terraform demo"),
        (["state machine", "transition", "ApplicationStatus"], "Application lifecycle"),
        (["domain event", "EventPublisher"], "Domain events"),
        (["exception", "validation", "ApiResponse"], "API errors & validation"),
        (["redis"], "Redis idempotency store"),
        (["jacoco", "coverage"], "Test coverage"),
        (["openapi", "swagger"], "API contract"),
    ]
    blob = title + " " + cat
    for keys, tag in mapping:
        if any(k.lower() in blob.lower() for k in keys):
            tags.append(tag)
    if not tags:
        if "Behavioral" in cat:
            tags.append("Project narrative / trade-offs")
        elif "DDD" in cat or "Domain-Driven" in cat:
            tags.append("Domain model")
        elif "Hexagonal" in cat or "Clean" in cat or "Repository Pattern" in cat:
            tags.append("Architecture boundaries")
        elif "Technical Debt" in cat or "System Design" in cat:
            tags.append("Evolution & limitations")
        elif "Project Overview" in cat:
            tags.append("Repository strategy")
        else:
            tags.append(cat)
    return list(dict.fromkeys(tags))


def tech_tags_for_question(q: dict) -> list[str]:
    cat = q["cat"]
    title = q["title"]
    blob = f"{cat} {title}"
    rules = [
        (r"Spring Boot|@SpringBoot|actuator", "Spring Boot"),
        (r"Spring Security|SecurityFilterChain|formLogin|authorizeHttpRequests", "Spring Security"),
        (r"JPA|Hibernate|Entity|@Table|JpaRepository", "JPA / Hibernate"),
        (r"Flyway|V\d+__", "Flyway"),
        (r"Redis|SETNX|idempotency\.store", "Redis"),
        (r"SQL Server|H2|jdbc", "SQL Server / H2"),
        (r"Docker|Compose|Dockerfile", "Docker"),
        (r"GitHub Actions|workflow_dispatch|ci\.yml", "GitHub Actions"),
        (r"Terraform|\.tf\b|hashicorp/local", "Terraform (local)"),
        (r"JUnit|Mockito|MockMvc|IntegrationTest", "JUnit / Mockito / MockMvc"),
        (r"JaCoCo|coverage", "JaCoCo"),
        (r"OpenAPI|springdoc|Swagger", "OpenAPI"),
        (r"AOP|@Aspect|@Auditable", "Spring AOP"),
        (r"Excel|PDF|Apache POI|OpenPDF", "Excel / PDF"),
        (r"Java 17|record|sealed|switch", "Java 17"),
        (r"Transaction|@Transactional", "Spring Transactions"),
        (r"MDC|logging|observab", "Logging / MDC"),
        (r"OTP", "OTP"),
        (r"In-Memory Cache|CacheStore", "In-memory cache"),
        (r"Scheduler|@Scheduled", "Scheduling"),
        (r"Hexagonal|Clean Architecture|Port|Adapter", "Hexagonal architecture"),
        (r"DDD|aggregate|Domain Event", "DDD"),
    ]
    tags = []
    for pat, tag in rules:
        if re.search(pat, blob, re.I):
            tags.append(tag)
    # category fallbacks
    cat_map = {
        "Spring Boot 3.x": "Spring Boot",
        "Spring Security": "Spring Security",
        "JPA and Hibernate": "JPA / Hibernate",
        "Flyway Migrations": "Flyway",
        "Redis and Idempotency": "Redis",
        "SQL Server and H2": "SQL Server / H2",
        "Docker and Docker Compose": "Docker",
        "GitHub Actions CI/CD": "GitHub Actions",
        "Terraform (Local)": "Terraform (local)",
        "Testing (JUnit + Mockito + MockMvc)": "JUnit / Mockito / MockMvc",
        "JaCoCo": "JaCoCo",
        "REST API Design and OpenAPI": "OpenAPI",
        "Audit Logging and AOP": "Spring AOP",
        "Reports (Excel and PDF)": "Excel / PDF",
        "Java 17 Features": "Java 17",
        "Transactions": "Spring Transactions",
        "Observability and Logging": "Logging / MDC",
        "OTP Flow": "OTP",
        "In-Memory Cache": "In-memory cache",
        "Schedulers": "Scheduling",
        "Clean / Hexagonal Architecture": "Hexagonal architecture",
        "Domain-Driven Design": "DDD",
        "Repository Pattern (Ports and Adapters)": "Hexagonal architecture",
        "Notifications (Mock)": "Mock integrations",
        "File Upload and Storage": "Local file storage",
        "Session Authentication vs JWT": "Spring Security",
        "Integration Testing": "JUnit / Mockito / MockMvc",
        "Validation and Exception Handling": "Bean Validation",
        "Application State Machine": "Domain model",
        "Credit Review Workflow": "Domain model",
        "Domain Events": "DDD",
    }
    if cat in cat_map:
        tags.append(cat_map[cat])
    if not tags:
        tags.append("General / repository knowledge")
    return list(dict.fromkeys(tags))


def q_link(q: dict) -> str:
    # Prefer explicit HTML anchors inserted before each question heading.
    return f"[{q['id']}](#{q['id']})"


def explain_java(path: str, info: dict, related: list[str], qs: dict) -> tuple[str, str]:
    name = info["name"]
    kind = info["kind"]
    layer = info["layer"]
    methods = ", ".join(f"`{m}()`" for m in info["methods"][:10]) or "no prominent public methods extracted"
    annos = ", ".join(f"`@{a}`" for a in info["annotations"][:8]) or "none prominent at type level"
    deps = ", ".join(f"`{d}`" for d in info["deps"][:8]) or "limited internal imports extracted"
    jd = info["javadoc"] or f"{kind} `{name}` in package `{info['package']}`."
    # Prefer Chinese-only fragment for 中文筆記 when javadoc is bilingual
    jd_zh = jd
    if re.search(r"[\u4e00-\u9fff]", jd) and re.search(r"[A-Za-z]{4,}", jd):
        # drop leading English sentence(s) before first CJK run when mixed
        m = re.search(r"[\u4e00-\u9fff].*", jd)
        if m:
            jd_zh = m.group(0)
    flows = []
    for qid in related[:6]:
        flows.extend(business_tags_for_question(qs[qid])[:1])
    flow = ", ".join(dict.fromkeys(flows)) or layer

    en = (
        f"**Main responsibility:** {jd}\n\n"
        f"**Important types:** `{kind} {name}` in `{info['package']}` (layer: {layer}).\n\n"
        f"**Annotations:** {annos}.\n\n"
        f"**Important methods / members:** {methods}.\n\n"
        f"**Direct dependencies (sampled imports):** {deps}.\n\n"
        f"**Business flow:** Appears in interview topics around: {flow}.\n\n"
        f"**Why open in an interview:** Confirms how this repository implements the claim under discussion "
        f"inside the {layer} layer rather than relying on a generic textbook pattern.\n\n"
        f"**Limitations / trade-offs:** Scope is whatever this class currently implements; do not assume "
        f"cloud, multi-region, or production hardening unless shown in neighboring adapters and config."
    )
    zh = (
        f"職責：{name}（{kind}）位於 `{info['package']}`，屬 {layer} 層。{jd_zh}\n"
        f"註解：{annos}。主要方法：{methods}。依賴取樣：{deps}。\n"
        f"業務關聯：{flow}。面談時用來對照該層實際實作，避免只背抽象名詞。\n"
        f"限制：僅代表檔案現況；未在相鄰 adapter／設定出現的雲端或高可用行為，不應外推。"
    )
    return en, zh


def explain_yaml(path: str, info: dict, related: list[str]) -> tuple[str, str]:
    if path.endswith("ci.yml") or "/workflows/" in path:
        jobs = ", ".join(f"`{j}`" for j in info["jobs"]) or "see file"
        triggers = ", ".join(f"`{t}`" for t in info.get("triggers") or []) or "`on:` block in file"
        en = (
            f"**Main responsibility:** GitHub Actions workflow `{info['workflow_name'] or Path(path).name}`.\n\n"
            f"**Triggers:** {triggers}.\n\n"
            f"**Jobs:** {jobs}.\n\n"
            f"**Runtime effect:** Orchestrates build, quality, scan, image, and/or deploy steps for this monorepo; "
            f"read job `needs:` and `if:` conditions in the file for exact sequencing "
            f"(for example `deploy-staging` may require `workflow_dispatch`).\n\n"
            f"**Why open in an interview:** Distinguishes automatic CI from manual `workflow_dispatch` deploy behavior.\n\n"
            f"**Limitations / trade-offs:** Self-hosted / local Docker assumptions in comments and steps are not "
            f"equivalent to managed cloud CD."
        )
        zh = (
            f"職責：GitHub Actions workflow `{info['workflow_name'] or Path(path).name}`。\n"
            f"觸發：{triggers}。工作：{jobs}。\n"
            f"執行效果依 `on`／`needs`／`if` 決定編譯、品質、掃描、映像與部署順序"
            f"（例如 `deploy-staging` 可能僅在 `workflow_dispatch` 執行）。\n"
            f"面談重點：分辨自動 CI 與手動部署。\n"
            f"限制：註解中的本機／self-hosted 假設不等於雲端自動交付。"
        )
        return en, zh
    if "docker-compose" in path:
        en = (
            f"**Main responsibility:** Compose file defining local multi-container topology.\n\n"
            f"**Sections:** Top-level keys include {', '.join(f'`{k}`' for k in info['top_keys'][:12])}.\n\n"
            f"**Runtime effect:** Starts dependent services (DB/Redis/app as declared) for local development.\n\n"
            f"**Why open in an interview:** Shows what actually runs locally versus what docs call “infrastructure”.\n\n"
            f"**Limitations / trade-offs:** Local compose is not production orchestration."
        )
        zh = (
            f"職責：本機多容器編排。頂層鍵：{', '.join(info['top_keys'][:12])}。\n"
            f"執行效果：依檔案宣告啟動 DB／Redis／應用等依賴。\n"
            f"面談重點：本機 runtime 與文件所稱基礎設施的差異。\n"
            f"限制：Compose 拓樸不是正式環境編排。"
        )
        return en, zh
    # application yml
    en = (
        f"**Main responsibility:** Spring / runtime configuration for profile or shared settings.\n\n"
        f"**Configuration sections:** {', '.join(f'`{k}`' for k in info['top_keys'][:15])}.\n\n"
        f"**Runtime effect:** Controls datasource, security, idempotency store, upload paths, and related "
        f"beans when the matching profile is active.\n\n"
        f"**Why open in an interview:** Properties cited in answers must match this file, not memory.\n\n"
        f"**Limitations / trade-offs:** Profile-specific files may override; absence of a key means behavior "
        f"falls back elsewhere or fails to wire."
    )
    zh = (
        f"職責：Spring／runtime 設定。頂層區段：{', '.join(info['top_keys'][:15])}。\n"
        f"執行效果：依 profile 影響資料源、安全、冪等儲存、上傳路徑等 bean 組裝。\n"
        f"面談重點：設定鍵必須與檔案一致。\n"
        f"限制：profile 覆寫或鍵缺失會改變實際行為。"
    )
    return en, zh


def explain_sql(path: str, info: dict, related: list[str]) -> tuple[str, str]:
    stmts = "; ".join(f"{a} {b}".strip() for a, b in info["statements"][:8]) or "see migration body"
    ver = info["version"] or "?"
    en = (
        f"**Main responsibility:** Flyway migration version `V{ver}` applying schema/data changes.\n\n"
        f"**Important sections:** {stmts}.\n\n"
        f"**Compatibility:** Must remain ordered with other `V*__*.sql` files; later migrations may alter "
        f"constraints introduced here.\n\n"
        f"**Domain relation:** Tables/columns here back JPA entities and repositories in `infrastructure.persistence`.\n\n"
        f"**Why open in an interview:** Proves whether a field/constraint exists in schema versus only in docs.\n\n"
        f"**Limitations / trade-offs:** SQL Server vs H2 differences may force migration compatibility choices."
    )
    zh = (
        f"職責：Flyway 遷移 `V{ver}`。主要語句：{stmts}。\n"
        f"相容性：須與其他 `V*__*.sql` 版本順序一致；後續遷移可能再改約束。\n"
        f"與領域關係：對應 `infrastructure.persistence` 的 entity／repository。\n"
        f"面談重點：欄位與約束是否真的落在 schema。\n"
        f"限制：SQL Server 與 H2 差異會影響遷移寫法。"
    )
    return en, zh


def explain_tf(path: str, info: dict) -> tuple[str, str]:
    if path.endswith(".tfstate"):
        en = (
            "**Main responsibility:** Local Terraform state snapshot for `infra/local`.\n\n"
            "**Important contents:** Records resource instances and outputs after `terraform apply`.\n\n"
            "**State implications:** Shows what the local provider created; not remote cloud state.\n\n"
            "**Why open in an interview:** Demonstrates local-only IaC evidence versus cloud accounts.\n\n"
            "**Limitations / trade-offs:** Committing state has collaboration/security trade-offs; scope remains local files."
        )
        zh = (
            "職責：`infra/local` 的本機 Terraform state。\n"
            "內容：記錄 apply 後的資源實例與 outputs。\n"
            "意涵：證明 local provider 產物，不是遠端雲端 state。\n"
            "面談重點：本機 IaC 與雲端帳號的界線。\n"
            "限制：state 入庫有協作與敏感資訊取捨；範圍仍是本機檔案。"
        )
        return en, zh
    res = ", ".join(f"`{t}.{n}`" for t, n in info["resources"]) or "none in this file"
    var = ", ".join(f"`{v}`" for v in info["variables"]) or "none"
    out = ", ".join(f"`{o}`" for o in info["outputs"]) or "none"
    prov = ", ".join(f"`{p}`" for p in info["providers"]) or "see `required_providers`"
    en = (
        f"**Main responsibility:** Terraform configuration under `infra/local` (local provider scope).\n\n"
        f"**Providers / resources:** providers {prov}; resources {res}.\n\n"
        f"**Variables / outputs:** variables {var}; outputs {out}.\n\n"
        f"**Why open in an interview:** Separates portfolio IaC practice from cloud provisioning claims.\n\n"
        f"**Limitations / trade-offs:** `hashicorp/local` creates local files only; no AWS/GCP/Azure resources here."
    )
    zh = (
        f"職責：`infra/local` Terraform 設定。provider：{prov}；resource：{res}。\n"
        f"變數／輸出：{var}；{out}。\n"
        f"面談重點：作品集 IaC 練習 ≠ 雲端建置。\n"
        f"限制：`hashicorp/local` 只產生本機檔案。"
    )
    return en, zh


def explain_md(path: str, info: dict, related: list[str]) -> tuple[str, str]:
    title = info["title"] or Path(path).name
    summary = info["summary"].replace("\n", " ")
    en = (
        f"**Main responsibility:** Documentation — `{title}`.\n\n"
        f"**Records:** {summary}\n\n"
        f"**Interview support:** Backs design/decision questions {', '.join(related[:8])}.\n\n"
        f"**Why open in an interview:** Establishes intended architecture or process before reading code.\n\n"
        f"**Limitations / trade-offs:** Docs can drift; verify critical claims against source and tests."
    )
    zh = (
        f"職責：文件《{title}》。摘要：{summary}\n"
        f"支援題目：{', '.join(related[:8])}。\n"
        f"面談用途：先讀意圖與決策，再對照程式。\n"
        f"限制：文件可能落後；關鍵主張需用原始碼與測試核對。"
    )
    return en, zh


def explain_docker(path: str, info: dict) -> tuple[str, str]:
    en = (
        f"**Main responsibility:** Container image build for the Spring Boot app.\n\n"
        f"**Stages / base images:** FROM {', '.join(info['froms']) or 'see file'}; "
        f"named stages {', '.join(info['stages']) or 'single-stage or unnamed'}.\n\n"
        f"**Users / ports / health:** USER {', '.join(info['users']) or 'default'}; "
        f"EXPOSE {', '.join(info['expose']) or 'none declared'}; "
        f"HEALTHCHECK {'present' if info['healthcheck'] else 'not declared in file'}.\n\n"
        f"**Why open in an interview:** Shows how the runnable artifact is packaged for Compose/CI.\n\n"
        f"**Limitations / trade-offs:** Image packaging ≠ production cluster scheduling or secrets management."
    )
    zh = (
        f"職責：應用容器映像建置。FROM：{', '.join(info['froms'])}；階段：{', '.join(info['stages']) or '無具名'}。\n"
        f"USER：{', '.join(info['users']) or '預設'}；EXPOSE：{', '.join(info['expose']) or '未宣告'}；"
        f"HEALTHCHECK：{'有' if info['healthcheck'] else '無'}。\n"
        f"面談用途：說明 Compose／CI 如何得到可執行映像。\n"
        f"限制：映像打包不等于叢集調度或密鑰治理。"
    )
    return en, zh


def explain_xml(path: str, content: str) -> tuple[str, str]:
    arts = re.findall(r"<artifactId>([^<]+)</artifactId>", content)
    deps = re.findall(r"<dependency>[\s\S]*?<artifactId>([^<]+)</artifactId>", content)
    en = (
        f"**Main responsibility:** Maven project descriptor `{Path(path).name}`.\n\n"
        f"**Important coordinates:** artifactIds sampled: {', '.join(f'`{a}`' for a in arts[:8])}.\n\n"
        f"**Dependencies / plugins:** {', '.join(f'`{d}`' for d in list(dict.fromkeys(deps))[:15])}.\n\n"
        f"**Runtime effect:** Defines Java version, Spring Boot parent, and test/coverage plugins used by CI.\n\n"
        f"**Why open in an interview:** Grounds stack claims (Boot version, JaCoCo, drivers) in build metadata.\n\n"
        f"**Limitations / trade-offs:** Declared dependency ≠ every feature is used at runtime."
    )
    zh = (
        f"職責：Maven 專案描述。artifactId 取樣：{', '.join(arts[:8])}。\n"
        f"依賴／外掛取樣：{', '.join(list(dict.fromkeys(deps))[:15])}。\n"
        f"效果：決定 Java／Spring Boot 版本與 CI 測試覆蓋外掛。\n"
        f"面談用途：用建置中繼資料核對技術棧主張。\n"
        f"限制：有依賴不代表執行期每個功能都有使用。"
    )
    return en, zh


def explain_generic(path: str, content: str) -> tuple[str, str]:
    en = (
        f"**Main responsibility:** Repository file `{path}` referenced by validated interview questions.\n\n"
        f"**Contents (excerpt):** {content[:350].replace(chr(10), ' ')}\n\n"
        f"**Why open in an interview:** Provides concrete evidence for answers that cite this path.\n\n"
        f"**Limitations / trade-offs:** Interpret only what the file contains; do not invent surrounding systems."
    )
    zh = (
        f"職責：題庫引用的 `{path}`。\n"
        f"內容摘錄：{content[:280].replace(chr(10), ' ')}\n"
        f"面談用途：作為路徑主張的直接證據。\n"
        f"限制：只依檔案內容解釋，不外推未出現的系統。"
    )
    return en, zh


def scrub_zh(text: str) -> str:
    # Remove forbidden tokens if accidentally introduced; keep 'AI' out of Chinese notes
    for w in FORBIDDEN_ZH:
        text = text.replace(w, "")
    text = re.sub(r"(?<![A-Za-z])AI(?![A-Za-z])", "輔助工具", text)
    text = text.replace("AI-", "輔助-")
    return text


def build_appendix_entry(path: str, related: list[str], qs: dict) -> tuple[str, str | None]:
    """Return (markdown, warning_or_none)."""
    fp = ROOT / path
    warning = None
    if not fp.exists():
        return "", f"missing:{path}"
    try:
        content = fp.read_text(encoding="utf-8", errors="replace")
    except Exception as e:
        return "", f"unreadable:{path}:{e}"

    suf = fp.suffix.lower()
    name = fp.name

    if suf == ".java":
        info = analyze_java(content, path)
        en, zh = explain_java(path, info, related, qs)
    elif suf in (".yml", ".yaml"):
        info = analyze_yaml(content)
        en, zh = explain_yaml(path, info, related)
    elif suf == ".sql":
        info = analyze_sql(content, path)
        en, zh = explain_sql(path, info, related)
    elif suf in (".tf", ".tfstate"):
        info = analyze_tf(content) if suf == ".tf" else {"resources": [], "variables": [], "outputs": [], "providers": []}
        if suf == ".tf":
            en, zh = explain_tf(path, info)
        else:
            en, zh = explain_tf(path, info)
    elif suf == ".md" or name.endswith(".md.example") or suf == ".example":
        if suf == ".example" and "env" in name.lower():
            en = (
                "**Main responsibility:** Example environment variable template for local/staging runs.\n\n"
                "**Important sections:** Key=value placeholders copied into a real env file (not committed secrets).\n\n"
                "**Runtime effect:** Documents required secrets/ports/URLs operators must supply.\n\n"
                "**Why open in an interview:** Shows which config is externalized versus hard-coded.\n\n"
                "**Limitations / trade-offs:** Example values are not production credentials."
            )
            zh = (
                "職責：本機／staging 環境變數範本。\n"
                "內容：key=value 佔位，複製到真實 env（密鑰不應提交）。\n"
                "效果：標出需外部提供的密鑰、埠、URL。\n"
                "面談用途：區分外部化設定與寫死常數。\n"
                "限制：範例值不是正式環境憑證。"
            )
        else:
            info = analyze_md(content)
            en, zh = explain_md(path, info, related)
    elif name == "Dockerfile" or name.startswith("Dockerfile"):
        info = analyze_docker(content)
        en, zh = explain_docker(path, info)
    elif suf == ".xml":
        en, zh = explain_xml(path, content)
    else:
        en, zh = explain_generic(path, content)
        warning = f"generic:{path}"

    zh = scrub_zh(zh)
    # Verify no forbidden remain
    for w in FORBIDDEN_ZH + ["AI"]:
        if w == "AI":
            if re.search(r"(?<![A-Za-z])AI(?![A-Za-z])", zh) or "AI-" in zh:
                zh = scrub_zh(zh)
        elif w in zh:
            zh = zh.replace(w, "")

    anchor = file_anchor(path)
    rel = "\n".join(f"- {qid}" for qid in related)
    md = (
        f'<a id="{anchor}"></a>\n\n'
        f"## `{path}`\n\n"
        f"### English explanation\n\n"
        f"{en}\n\n"
        f"### 中文筆記\n\n"
        f"{zh}\n\n"
        f"### Related questions\n\n"
        f"{rel}\n"
    )
    return md, warning


def build_indexes(questions: list[dict], file_to_qs: dict[str, list[str]]) -> str:
    qs = {q["id"]: q for q in questions}

    # TOC
    toc_lines = [
        "# TLBank Interview Source Map (300)",
        "",
        "Repository-grounded interview questions validated against the current `sp2-springboot` codebase and related monorepo paths.",
        "",
        "> Draft source: `sp2-springboot/docs/drafts/interview-question-catalog-draf.md`. Claims were checked against source; planned or partial behavior is labelled in notes where relevant.",
        "",
        "---",
        "",
        "## Table of Contents",
        "",
        "- [Category Index](#category-index)",
        "- [Technology Index](#technology-index)",
        "- [Business Feature Index](#business-feature-index)",
        "- [File Path Index](#file-path-index)",
        "- [Difficulty Index](#difficulty-index)",
        "- [Questions Q001–Q300](#questions-q001q300)",
        "- [Referenced File Appendix](#referenced-file-appendix)",
        "",
        "---",
        "",
        "## Category Index",
        "",
    ]

    by_cat = defaultdict(list)
    for q in questions:
        by_cat[q["cat"]].append(q)
    for cat in sorted(by_cat.keys()):
        ids = ", ".join(q_link(q) for q in by_cat[cat])
        toc_lines.append(f"### {cat}")
        toc_lines.append("")
        toc_lines.append(ids)
        toc_lines.append("")

    toc_lines += ["---", "", "## Technology Index", ""]
    by_tech = defaultdict(list)
    for q in questions:
        for t in tech_tags_for_question(q):
            by_tech[t].append(q)
    for tech in sorted(by_tech.keys()):
        # unique preserve order
        seen = []
        for q in by_tech[tech]:
            if q["id"] not in {x["id"] for x in seen}:
                seen.append(q)
        toc_lines.append(f"### {tech}")
        toc_lines.append("")
        toc_lines.append(", ".join(q_link(q) for q in seen))
        toc_lines.append("")

    toc_lines += ["---", "", "## Business Feature Index", ""]
    by_biz = defaultdict(list)
    for q in questions:
        for t in business_tags_for_question(q):
            by_biz[t].append(q)
    for biz in sorted(by_biz.keys()):
        seen = []
        for q in by_biz[biz]:
            if q["id"] not in {x["id"] for x in seen}:
                seen.append(q)
        toc_lines.append(f"### {biz}")
        toc_lines.append("")
        toc_lines.append(", ".join(q_link(q) for q in seen))
        toc_lines.append("")

    toc_lines += ["---", "", "## File Path Index", ""]
    toc_lines.append("Each path links to its appendix entry. Question IDs link to the question heading.")
    toc_lines.append("")
    for path in sorted(file_to_qs.keys()):
        qids = file_to_qs[path]
        q_links = ", ".join(f"[{qid}](#{qid})" for qid in qids)
        toc_lines.append(f"- [`{path}`](#{file_anchor(path)}) — {q_links}")
    toc_lines.append("")

    toc_lines += ["---", "", "## Difficulty Index", ""]
    for diff in ("Basic", "Intermediate", "Advanced"):
        group = [q for q in questions if q["diff"] == diff]
        toc_lines.append(f"### {diff} ({len(group)})")
        toc_lines.append("")
        toc_lines.append(", ".join(q_link(q) for q in group))
        toc_lines.append("")

    toc_lines += [
        "---",
        "",
        "## Questions Q001–Q300",
        "",
        "Validated question bodies follow. Numbering, categories, difficulty, and technical content are unchanged from the source map validation pass.",
        "",
        "---",
        "",
    ]
    return "\n".join(toc_lines)


def main() -> None:
    text = SRC.read_text(encoding="utf-8")
    # If already enhanced, strip previous indexes/appendix and keep question bodies
    if "## Referenced File Appendix" in text or "# Referenced File Appendix" in text:
        # extract from Q001 through end of Q300 only
        m0 = re.search(r"^## Q001 —", text, re.M)
        m_app = re.search(r"^# Referenced File Appendix", text, re.M)
        if m0 and m_app:
            text = text[m0.start() : m_app.start()].rstrip() + "\n"
        elif m0:
            text = text[m0.start() :]
        # rebuild minimal preamble marker
        text = "# PLACEHOLDER\n\n" + text

    pre, questions, _ = parse_questions(text if text.startswith("#") else "# x\n\n" + text)
    # If we used placeholder, re-parse from file more carefully
    raw = SRC.read_text(encoding="utf-8")
    # Prefer current on-disk questions: find Q001..Q300 block even if indexes already exist
    m0 = re.search(r"^## Q001 —", raw, re.M)
    # End before appendix if present
    m_app = re.search(r"^# Referenced File Appendix\s*$", raw, re.M)
    q_section = raw[m0.start() : m_app.start() if m_app else len(raw)]
    # Remove trailing --- after Q300 if appendix was not there
    _, questions, _ = parse_questions("# tmp\n\n" + q_section)

    assert len(questions) == 300, len(questions)
    assert questions[0]["id"] == "Q001" and questions[-1]["id"] == "Q300"

    file_to_qs: dict[str, list[str]] = defaultdict(list)
    for q in questions:
        for f in q["files"]:
            if q["id"] not in file_to_qs[f]:
                file_to_qs[f].append(q["id"])

    qs = {q["id"]: q for q in questions}
    index_md = build_indexes(questions, file_to_qs)

    # Question bodies with stable HTML anchors for fallback navigation
    q_parts = []
    for q in questions:
        body = q["body"].rstrip()
        # Ensure body starts with ## Qxxx
        if not body.startswith("## "):
            raise SystemExit(f"bad body {q['id']}")
        # Insert anchor before heading if not present
        if f'<a id="{q["id"]}"></a>' not in body:
            body = f'<a id="{q["id"]}"></a>\n\n' + body
        # Ensure slug-friendly: also keep original heading for github_slug links
        if not body.endswith("\n"):
            body += "\n"
        q_parts.append(body)

    questions_md = "\n\n---\n\n".join(q_parts) + "\n"

    # Appendix
    warnings = []
    appendix_parts = [
        "# Referenced File Appendix",
        "",
        "Unique files listed under **Verified source files** for Q001–Q300. Each path appears once.",
        "",
        f"Total unique files: **{len(file_to_qs)}**.",
        "",
        "---",
        "",
    ]
    for path in sorted(file_to_qs.keys()):
        md, warn = build_appendix_entry(path, file_to_qs[path], qs)
        if warn:
            warnings.append(warn)
        if not md:
            warnings.append(f"empty:{path}")
            continue
        appendix_parts.append(md.rstrip())
        appendix_parts.append("")
        appendix_parts.append("---")
        appendix_parts.append("")

    appendix_md = "\n".join(appendix_parts).rstrip() + "\n"

    final = index_md + questions_md + "\n---\n\n" + appendix_md

    # Markdown hygiene: no trailing spaces
    final = "\n".join(line.rstrip() for line in final.splitlines()) + "\n"
    # Collapse 4+ blank lines
    final = re.sub(r"\n{4,}", "\n\n\n", final)

    OUT.write_text(final, encoding="utf-8")

    # Stats
    exts = defaultdict(int)
    for f in file_to_qs:
        if "Dockerfile" in Path(f).name:
            exts["Dockerfile"] += 1
        else:
            exts[Path(f).suffix.lower() or "(none)"] += 1

    report = {
        "unique_files": len(file_to_qs),
        "appendix_entries": len(file_to_qs),
        "by_type": dict(exts),
        "warnings": warnings,
        "questions": len(questions),
        "bytes": OUT.stat().st_size,
        "lines": final.count("\n") + 1,
    }
    Path("/tmp/enhance_report.json").write_text(
        __import__("json").dumps(report, indent=2), encoding="utf-8"
    )
    print(__import__("json").dumps(report, indent=2))


if __name__ == "__main__":
    main()
