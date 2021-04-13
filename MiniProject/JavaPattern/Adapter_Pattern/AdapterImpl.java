package Adapter_Pattern;

public class AdapterImpl implements Adapter {

	@Override
	public Float twiceOf(Float f) {

		//기존의 float 타입을 double로 변환
		return (float)Math.twoTime(f.doubleValue());
	}

	@Override
	public Float halfOf(Float f) {
		
		//기존의 float 타입을 double로 변환
		return (float)Math.half(f.doubleValue());
	}

}
