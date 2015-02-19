import java.math.BigDecimal;
import java.math.MathContext;


public class PiThread extends Thread {
	
	private int from;
	private int to;
	private int presicion;
	private BigDecimal result;
	
	static final BigDecimal one = BigDecimal.ONE;
	static final BigDecimal two = new BigDecimal(2);
	static final BigDecimal four = new BigDecimal(4);
	static final BigDecimal sixteen = new BigDecimal(16);

	public PiThread(int from, int to, int presicion) {
		this.from = from;
		this.to = to;
		this.presicion = presicion;
	}
	
	@Override
	public void run() {
		BigDecimal pi = getPi(from, to, presicion);
		result = pi;
	}
	
	public BigDecimal getResult() {
		return result;
	}

	public static BigDecimal getPi(int limit) {
		return getPi(0, limit, limit);
	}

	public static BigDecimal getPi(int from, int to, int precision) {
		return getPi(from, to, new MathContext(precision));
	}

	public static BigDecimal getPi(int from, int to, MathContext precision) {
		BigDecimal pi = BigDecimal.ZERO;
		for (int k = from; k < to; k++) {
			pi = pi.add(one.divide(sixteen.pow(k), precision).multiply(four.divide(new BigDecimal(8 * k + 1), precision).subtract(two.divide(new BigDecimal(8 * k + 4), precision)).subtract(one.divide(new BigDecimal(8 * k + 5), precision)).subtract(one.divide(new BigDecimal(8 * k + 6), precision))));
		}
		return pi;
	}

}
