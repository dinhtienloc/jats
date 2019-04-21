package vn.locdt.jats.module.generator.context;

import vn.locdt.jats.module.generator.FileType;

import java.util.HashMap;

public abstract class XHTMLContext<CM> extends GenerationContext<CM> {
	private XHTMLContext(String outputDirectory, String outputName) {
		super(outputDirectory, outputName, FileType.XHTML);
	}

	public XHTMLContext(CM contextModel, String outputDirectory, String outputName) {
		this(outputDirectory, outputName);
		this.contextModel = contextModel;
	}

	public XHTMLContext(CM contextModel) {
		this();
		this.contextModel = contextModel;
	}

	public XHTMLContext() {
		super(FileType.XHTML);
	}
}
