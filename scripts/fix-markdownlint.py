#!/usr/bin/env python3
"""Batch-fix common markdownlint issues (MD022, MD031, MD032, MD060, MD012)."""

from __future__ import annotations

import re
import sys
from pathlib import Path


def parse_table_row(line: str) -> list[str] | None:
    line = line.strip()
    if not line.startswith("|"):
        return None
    inner = line[1:]
    if inner.endswith("|"):
        inner = inner[:-1]
    cells: list[str] = []
    current: list[str] = []
    in_backtick = False
    for ch in inner:
        if ch == "`":
            in_backtick = not in_backtick
            current.append(ch)
        elif ch == "|" and not in_backtick:
            cells.append("".join(current).strip())
            current = []
        else:
            current.append(ch)
    cells.append("".join(current).strip())
    return cells


def is_separator_row(cells: list[str]) -> bool:
    return bool(cells) and all(re.fullmatch(r":?-{3,}:?", c.strip()) for c in cells)


def format_table_block(lines: list[str]) -> list[str]:
    parsed = [parse_table_row(line) for line in lines]
    rows = [r for r in parsed if r is not None]
    if not rows:
        return lines

    ncol = max(len(r) for r in rows)
    rows = [r + [""] * (ncol - len(r)) for r in rows]
    widths = [max(len(rows[i][c]) for i in range(len(rows))) for c in range(ncol)]

    out: list[str] = []
    for i, row in enumerate(rows):
        is_sep = is_separator_row(row) or (i == 1 and all(c.strip("-:") == "" for c in row))
        parts: list[str] = []
        for j in range(ncol):
            w = max(3, widths[j])
            if is_sep:
                parts.append(f" {'-' * w} ")
            else:
                cell = row[j] if j < len(row) else ""
                parts.append(f" {cell.ljust(w)} ")
        out.append("|" + "|".join(parts) + "|")
    return out


def fix_markdown(content: str) -> str:
    lines = content.splitlines()
    result: list[str] = []
    i = 0

    while i < len(lines):
        line = lines[i]

        # Table block
        if line.strip().startswith("|"):
            block: list[str] = []
            while i < len(lines) and lines[i].strip().startswith("|"):
                block.append(lines[i])
                i += 1
            result.extend(format_table_block(block))
            continue

        # Heading: ensure blank line below (MD022)
        if re.match(r"^#{1,6} ", line):
            if result and result[-1].strip() != "":
                result.append("")
            result.append(line)
            if i + 1 < len(lines) and lines[i + 1].strip() != "":
                result.append("")
            i += 1
            continue

        # Fenced code block: blank line before opening fence (MD031)
        if line.startswith("```") and result and result[-1].strip() != "":
            result.append("")

        # List: blank line before list item (MD032)
        if re.match(r"^(\*|-|\d+\.) ", line) and result:
            prev = result[-1]
            if prev.strip() and not re.match(r"^(\*|-|\d+\.) ", prev) and not prev.startswith("```"):
                result.append("")

        result.append(line)

        # Fenced code block: blank line after closing fence (MD031)
        if line.startswith("```") and line.strip() != "```":
            # opening fence with language — handled on close
            pass
        elif line.strip() == "```" or (line.startswith("```") and i > 0):
            # closing fence: check if next line is non-blank non-fence
            if i + 1 < len(lines):
                nxt = lines[i + 1]
                if nxt.strip() and not nxt.startswith("```") and not re.match(r"^#{1,6} ", nxt):
                    # don't add if next is list — list handler adds before
                    if not re.match(r"^(\*|-|\d+\.) ", nxt):
                        pass  # defer to next iteration

        i += 1

    text = "\n".join(result)

    # Collapse 3+ blank lines to 1 (MD012)
    text = re.sub(r"\n{3,}", "\n\n", text)

    # Single trailing newline (MD047)
    text = text.rstrip("\n") + "\n"
    return text


def apply_manual_fixes(path: Path, content: str) -> str:
    rel = path.as_posix()

    if rel.endswith("sp2-springboot/docs/05-database-design.md"):
        content = content.replace(
            "| String concatenation in seed data | `||` | `+` |",
            "| String concatenation in seed data | H2 concat operator (two pipe chars) | T-SQL concat operator (`+`) |",
        )

    if rel.endswith("sp2-springboot/docs/06-api-specification.md"):
        content = content.replace(
            "| `PUT` | `/api/v1/admin/users/{userId}/status?enabled=true|false` | Enable/disable a user account |",
            "| `PUT` | `/api/v1/admin/users/{userId}/status?enabled=true\\|false` | Enable/disable a user account |",
        )

    if rel.endswith("sp2-springboot/docs/07-security-design.md"):
        content = content.replace(
            "| Authenticated but insufficient role | `CustomAccessDeniedHandler` | `403` + `ApiResponse.error(FORBIDDEN)` (always JSON — no browser-specific branch) |",
            "| Authenticated but insufficient role | `CustomAccessDeniedHandler` | `403` + `ApiResponse.error(FORBIDDEN)` | — (always JSON; no browser-specific branch) |",
        )
        content = content.replace(
            '| Session invalidated by concurrent login | `SessionExpiredStrategy` | `401` + `ApiResponse.error(UNAUTHORIZED, "Session expired due to concurrent login")` |',
            '| Session invalidated by concurrent login | `SessionExpiredStrategy` | `401` + `ApiResponse.error(UNAUTHORIZED, "Session expired due to concurrent login")` | — (same JSON response) |',
        )

    if rel.endswith("sp2-springboot/HELP.md") or rel.endswith("sp2-springboot-test/HELP.md"):
        content = content.replace("### Reference Documentation", "## Reference Documentation")
        content = content.replace("### Guides", "## Guides")
        content = content.replace("### Maven Parent overrides", "## Maven Parent overrides")

    if rel.endswith("sp2-springboot/.cursor/rules.md"):
        if not content.lstrip().startswith("#"):
            content = "# Cursor Rules\n\n" + content

    if rel.endswith("infra/local/README.md") and not content.strip():
        content = "# Local Infrastructure\n\nTerraform configuration for local staging environment validation.\n"

    return content


def main() -> int:
    root = Path(sys.argv[1] if len(sys.argv) > 1 else ".")
    md_files = sorted(
        p
        for p in root.rglob("*.md")
        if "node_modules" not in p.parts and "target" not in p.parts
    )

    for path in md_files:
        if ".cursor" in path.parts:
            continue
        original = path.read_text(encoding="utf-8")
        updated = fix_markdown(apply_manual_fixes(path, original))
        if updated != original:
            path.write_text(updated, encoding="utf-8")
            print(f"updated: {path.relative_to(root)}")

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
