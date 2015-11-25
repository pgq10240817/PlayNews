package android.yhpl.core.http.parser;

public class ParserFacotory implements ParserTag {
	private static ParserFacotory instance;

	public static ParserFacotory getFacotory() {
		if (instance == null) {
			synchronized (ParserFacotory.class) {
				if (instance == null) {
					instance = new ParserFacotory();
				}
			}
		}
		return instance;
	}

	public BaseDecorator getBeanDecortor(int decoratorType) {
		BaseDecorator result = null;
		switch (decoratorType) {
		case ParserTag.HTTP_PARSER_AD:
			result = new DecoratorAd();
			break;
		case ParserTag.HTTP_PARSER_CHANNEL:
			result = new DecoratorChannels();
			break;
		case ParserTag.HTTP_PARSER_NEWS:
			result = new DecoratorNews();
			break;
		default:
			break;
		}
		return result;

	}
}
