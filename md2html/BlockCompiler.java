package md2html;

import java.util.Map;

public class BlockCompiler {

	private final String block;
	private int iterator = 0;
	private final StringBuilder result;
	private final Map<String, String> md2htmlTags = Map.of(
			"*", "em",
			"**", "strong",
			"_", "em",
			"__", "strong",
			"--", "s",
			"`", "code",
			"<", "&lt;",
			">", "&gt;",
			"&", "&amp;");

	public BlockCompiler(String block) {
		this.result = new StringBuilder();
		this.block = block;
		int headerLvl = getHeaderLvl(this.block);
		if (headerLvl > 0) {
			result.append(String.format("<h%d>", headerLvl));
			iterator = headerLvl + 1;
			result.append(compile(""));
			result.append(String.format("</h%d>", headerLvl));
		} else {
			result.append("<p>");
			result.append(compile(""));
			result.append("</p>");
		}
	}

	public String getBlock() {
		return result.toString();
	}

	private String compile(String blockTag) {
		StringBuilder accum = new StringBuilder();
		String mdTag = "";
		String hTag = "";
		while (iterator < block.length()) {
			char smb = block.charAt(iterator);
			if (smb == '*' || smb == '_') {
				if (iterator + 1 < block.length() && smb == block.charAt(iterator + 1)) {
					mdTag = block.substring(iterator, iterator + 2);
					hTag = md2htmlTags.get(mdTag);
					iterator++;
				} else {
					mdTag = Character.toString(smb);
					hTag = md2htmlTags.get(mdTag);
				}
			} else if (smb == '-' && iterator + 1 < block.length() && smb == block.charAt(iterator + 1)) {
				mdTag = block.substring(iterator, iterator + 2);
				hTag = md2htmlTags.get(mdTag);
				iterator++;
			} else if (smb == '`') {
				mdTag = Character.toString(smb);
				hTag = md2htmlTags.get(mdTag);
			} else if (smb == '!') {
				String source = block.substring(iterator);
				int altRight = source.indexOf("]");
				int srcRight = source.indexOf(")");
				if (altRight > -1 && srcRight > -1) {
					String alt = source.substring(2, altRight);
					String src = source.substring(altRight + 2, srcRight);
					accum.append(String.format("<img alt='%s' src='%s'>", alt, src));
					iterator += srcRight;
				}
			} else if (smb == '&' || smb == '>' || smb == '<') {
				accum.append(md2htmlTags.get(Character.toString(smb)));
			} else if (smb == '\\') {
				iterator++;
				accum.append(block.charAt(iterator));
			} else {
				accum.append(smb);
			}
			if (mdTag.equals(blockTag) && !mdTag.isEmpty()) {
				accum.append(String.format("</%s>", hTag));
				return accum.toString();
			}
			iterator++;
			if (!mdTag.isEmpty()) {
				String newLine = compile(mdTag);
				if (newLine.length() >= hTag.length() &&
						newLine.substring(newLine.length() - hTag.length() - 3)
								.equals(String.format("</%s>", hTag))) {
					accum.append(String.format("<%s>", hTag)).append(newLine);
					iterator++;
				} else {
					accum.append(mdTag).append(newLine);
				}
				mdTag = "";
			}
		}
		return accum.toString();
	}

	private int getHeaderLvl(String block) {
		int lvl = 0;
		while (lvl < block.length() && block.charAt(lvl) == '#') {
			lvl++;
		}
		if (lvl < block.length() && block.charAt(lvl) == ' ') {
			return lvl;
		} else {
			return 0;
		}
	}
}
