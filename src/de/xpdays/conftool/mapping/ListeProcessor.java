package de.xpdays.conftool.mapping;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.util.CSVContext;

import com.google.common.base.Joiner;

abstract class ListeProcessor implements CellProcessor {

	private final Pattern pattern;

	public ListeProcessor(String regex) {
		this.pattern = Pattern.compile(regex);
	}

	@Override
	public Object execute(Object value, CSVContext context) {
		if (value instanceof String) {
			List<String> names = new LinkedList<String>();
			String text = (String) value;
			Matcher matcher = pattern.matcher(text);
			while (matcher.find()) {
				String group = matcher.group(1);
				names.add(postprocess(group));
			}
			return names.toArray(new String[names.size()]);
		} else {
			return Joiner.on("; ").join((Collection<?>) value);
		}
	}

	protected abstract String postprocess(String item);

}
