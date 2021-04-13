package Adapter_Pattern;

public class AdapterImpl implements Adapter {

	@Override
	public Float twiceOf(Float f) {

		//������ float Ÿ���� double�� ��ȯ
		return (float)Math.twoTime(f.doubleValue());
	}

	@Override
	public Float halfOf(Float f) {
		
		//������ float Ÿ���� double�� ��ȯ
		return (float)Math.half(f.doubleValue());
	}

}
