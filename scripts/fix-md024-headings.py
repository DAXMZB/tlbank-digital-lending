#!/usr/bin/env python3
"""Prefix repeated ### headings in architecture-handbook.md with feature context."""

from __future__ import annotations

import re
import sys
from pathlib import Path

SHORT_NAMES = {
    "Authentication & Login": "Authentication",
    "Schedulers (Background Jobs)": "Schedulers",
}

SUFFIXES = (
    "Execution Flow",
    "Sequence Diagram",
    "Important Classes",
    "Related Files",
    "Folder Structure",
    "Dependency Graph",
)


def feature_from_h2(line: str) -> str | None:
    match = re.match(r"^## \d+\. (.+)$", line)
    if not match:
        return None
    return SHORT_NAMES.get(match.group(1), match.group(1))


def prefixed_heading(text: str, feature: str) -> str | None:
    for suffix in SUFFIXES:
        if text == suffix:
            return f"{feature} {suffix}"
        if text.startswith(f"{suffix} —") or text.startswith(f"{suffix}—"):
            return f"{feature} {text}"
    return None


def fix_content(content: str) -> tuple[str, list[tuple[str, str]]]:
    lines = content.splitlines()
    feature: str | None = None
    changes: list[tuple[str, str]] = []

    for i, line in enumerate(lines):
        h2 = feature_from_h2(line)
        if h2 is not None:
            feature = h2
            continue

        match = re.match(r"^(### )(.+)$", line)
        if not match or feature is None:
            continue

        old = match.group(2)
        new = prefixed_heading(old, feature)
        if new and new != old:
            lines[i] = f"### {new}"
            changes.append((old, new))

    return "\n".join(lines) + ("\n" if content.endswith("\n") else ""), changes


def main() -> int:
    root = Path(sys.argv[1] if len(sys.argv) > 1 else ".")
    path = root / "sp2-springboot/docs/architecture-handbook.md"
    original = path.read_text(encoding="utf-8")
    updated, changes = fix_content(original)
    if updated != original:
        path.write_text(updated, encoding="utf-8")
    for old, new in changes:
        print(f"  {old!r} -> {new!r}")
    print(f"Renamed {len(changes)} headings in {path.relative_to(root)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
