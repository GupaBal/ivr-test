package SCM;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Scm_eunms.ScmInfoType;

class TestTDD {

	@Test
	void test() {


				ArrayList<ScmInfoType> enums = new ArrayList<ScmInfoType>();
				enums.add(ScmInfoType.ARSEXTENSION.setPayload("3201"));
				enums.add(ScmInfoType.AGT_CALL.setPayload(""));
				enums.add(ScmInfoType.FLAG.setPayload("2"));
				enums.add(ScmInfoType.FLAG);
				for (int i = 0; i < enums.size(); i++) {

				    System.out.println(enums.get(i) + " ");



		}

	}

}
