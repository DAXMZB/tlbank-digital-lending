#!/usr/bin/env python3
"""Normalize Markdown tables to MD060 compact style: one space around every pipe."""

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
    escaped = False
    for ch in inner:
        if escaped:
            current.append(ch)
            escaped = False
            continue
        if ch == "\\":
            current.append(ch)
            escaped = True
            continue
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


def cell_padding(cell: str, separator: bool = False) -> str:
    if separator:
        return " --- "
    if cell == "":
        return " "
    return f" {cell} "


def format_compact_row(cells: list[str], separator: bool = False) -> str:
    parts = [cell_padding(cell, separator=separator) for cell in cells]
    return "|" + "|".join(parts) + "|"


def format_table_block(lines: list[str]) -> list[str]:
    parsed = [parse_table_row(line) for line in lines]
    rows = [r for r in parsed if r is not None]
    if not rows:
        return lines

    ncol = max(len(r) for r in rows)
    rows = [r + [""] * (ncol - len(r)) for r in rows]

    out: list[str] = []
    for i, row in enumerate(rows):
        sep = is_separator_row(row)
        if not sep and i == 1 and all(c.strip("-:") == "" for c in row):
            sep = True
        out.append(format_compact_row(row, separator=sep))
    return out


def fix_tables(content: str) -> str:
    lines = content.splitlines()
    result: list[str] = []
    i = 0
    while i < len(lines):
        if lines[i].strip().startswith("|"):
            block: list[str] = []
            while i < len(lines) and lines[i].strip().startswith("|"):
                block.append(lines[i])
                i += 1
            result.extend(format_table_block(block))
            continue
        result.append(lines[i])
        i += 1
    text = "\n".join(result)
    if content.endswith("\n"):
        text = text.rstrip("\n") + "\n"
    return text


def main() -> int:
    root = Path(sys.argv[1] if len(sys.argv) > 1 else ".")
    md_files = sorted(
        p
        for p in root.rglob("*.md")
        if "node_modules" not in p.parts and "target" not in p.parts and ".cursor" not in p.parts
    )
    for path in md_files:
        original = path.read_text(encoding="utf-8")
        updated = fix_tables(original)
        if updated != original:
            path.write_text(updated, encoding="utf-8")
            print(f"updated: {path.relative_to(root)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
