
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CamelCaseConvertesTest {

	private CamelCaseConverter camelCase;

	@Before
	public void setup() {
		camelCase = new CamelCaseConverter();
	}

	@Test
	public void deveConverterNomeSimples() throws Exception {
		Assert.assertEquals("Lionel", camelCase.converter("lionel"));
	}
	
	@Test
	public void deveConvetrerNomeSimplesMisturadoMaiusculoEMinusculo() throws Exception {
		Assert.assertEquals("Lionel", camelCase.converter("lIOnel"));
	}
	
}
