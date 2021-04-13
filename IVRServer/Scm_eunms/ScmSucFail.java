package Scm_eunms;


public enum ScmSucFail {
	Success("1"), Fail("2"), ScmNoResponse("3");
	String value;

	private ScmSucFail(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
