package sigoper.impl;

import sigoper.*;
import sig.Signature;

import java.util.Iterator;

/**
 * A Savitzky-Golay filter.
 * Some filter definitions are also available. See 'filters'.
 * @author Carlos A. Rueda
 * @version $Id$ 
 */
public class SavitzkyGolayFilter
{
	int M;   // just informative.
	int nL;
	int nR;
	double[] coeffs;
	
	/**
	 * Creates a filter with given parameters and coefficients.
	 */
	public SavitzkyGolayFilter(int M, int nL, int nR, double[] coeffs)
	{
		this.M = M;
		this.nL = nL;
		this.nR = nR;
		this.coeffs = coeffs;
		if ( coeffs.length != nL + 1 + nR )
		{
			throw new RuntimeException("coeffs.length != nL + 1 + nR");
		}
	}

	/**
	 * Returns "SG:M-nL-nR"
	 */
	public String toString()
	{
		return "SG:" +M+ "-" +nL+ "-" +nR;
	}

	/**
	 * Applies this filter on a signature.
	 *
	 * @param sig  The signature to be operated.
	 * @param from From what abscissa to apply
	 * @param to   To what abscissa to apply
	 *
	 * @return     The resulting signature.
	 */
	public Signature operate(Signature sig, double from, double to)
	throws OperationException
	{
		int size = sig.getSize();
		Signature new_sig = new Signature();
		for ( int i = 0; i < size; i++ )
		{
			Signature.Datapoint dp = sig.getDatapoint(i);
			double y = dp.y;
			
			if ( from <= dp.x && dp.x <= to )   // in range to smooth.
			{
				y = 0.0;
				int k = 0;
				for ( int n = i - this.nL; n <= i + this.nR; n++, k++ )
				{
					if ( 0 <= n && n < size )
					{
						Signature.Datapoint dq = sig.getDatapoint(n);
						y += this.coeffs[k] * dq.y;
					}
				}
			}

			new_sig.addDatapoint(dp.x, y);
		}

		return new_sig;
	}


	/** Available filters: (the format is M-nL-nR)
	 *		<ul>
	 *			<li> 3-40-40
	 *			<li> 3-30-30
	 *			<li> 3-20-20
	 *			<li> 3-12-12
	 *			<li> 2-25-25
	 *			<li> 2-20-20
	 *			<li> 2-15-15
	 *			<li> 2-10-10
	 *			<li> 2-5-5
	 *			<li> 2-2-2
	 *			<li> 2-3-1
	 *			<li> 2-4-0
	 *			<li> 2-5-5
	 *			<li> 4-4-4
	 *			<li> 4-5-5
	 *      </ul>
	 */
	public static final SavitzkyGolayFilter[] filters = {
		
	// Computed from create-savitzky-golay-filter program:
	
		new SavitzkyGolayFilter(3, 40, 40, new double[]{
			-0.017403,
			-0.015172,
			-0.012997,
			-0.010879,
			-0.008817,
			-0.006812,
			-0.004863,
			-0.002971,
			-0.001135,
			0.000644,
			0.002367,
			0.004033,
			0.005643,
			0.007196,
			0.008693,
			0.010133,
			0.011517,
			0.012845,
			0.014116,
			0.015330,
			0.016488,
			0.017589,
			0.018634,
			0.019623,
			0.020555,
			0.021430,
			0.022249,
			0.023012,
			0.023718,
			0.024368,
			0.024961,
			0.025497,
			0.025977,
			0.026401,
			0.026768,
			0.027079,
			0.027333,
			0.027531,
			0.027672,
			0.027757,
			0.027785,
			0.027757,
			0.027672,
			0.027531,
			0.027333,
			0.027079,
			0.026768,
			0.026401,
			0.025977,
			0.025497,
			0.024961,
			0.024368,
			0.023718,
			0.023012,
			0.022249,
			0.021430,
			0.020555,
			0.019623,
			0.018634,
			0.017589,
			0.016488,
			0.015330,
			0.014116,
			0.012845,
			0.011517,
			0.010133,
			0.008693,
			0.007196,
			0.005643,
			0.004033,
			0.002367,
			0.000644,
			-0.001135,
			-0.002971,
			-0.004863,
			-0.006812,
			-0.008817,
			-0.010879,
			-0.012997,
			-0.015172,
			-0.017403
		}),
		new SavitzkyGolayFilter(3, 30, 30, new double[]{
			-0.022639,
			-0.018735,
			-0.014964,
			-0.011326,
			-0.007820,
			-0.004446,
			-0.001204,
			0.001905,
			0.004882,
			0.007727,
			0.010439,
			0.013019,
			0.015467,
			0.017783,
			0.019966,
			0.022017,
			0.023935,
			0.025721,
			0.027375,
			0.028897,
			0.030286,
			0.031543,
			0.032668,
			0.033660,
			0.034520,
			0.035248,
			0.035843,
			0.036306,
			0.036637,
			0.036836,
			0.036902,
			0.036836,
			0.036637,
			0.036306,
			0.035843,
			0.035248,
			0.034520,
			0.033660,
			0.032668,
			0.031543,
			0.030286,
			0.028897,
			0.027375,
			0.025721,
			0.023935,
			0.022017,
			0.019966,
			0.017783,
			0.015467,
			0.013019,
			0.010439,
			0.007727,
			0.004882,
			0.001905,
			-0.001204,
			-0.004446,
			-0.007820,
			-0.011326,
			-0.014964,
			-0.018735,
			-0.022639
		}),
		new SavitzkyGolayFilter(3, 20, 20, new double[]{
			-0.032331,
			-0.023823,
			-0.015751,
			-0.008116,
			-0.000916,
			0.005847,
			0.012173,
			0.018064,
			0.023518,
			0.028535,
			0.033117,
			0.037262,
			0.040970,
			0.044243,
			0.047079,
			0.049479,
			0.051442,
			0.052969,
			0.054060,
			0.054714,
			0.054933,
			0.054714,
			0.054060,
			0.052969,
			0.051442,
			0.049479,
			0.047079,
			0.044243,
			0.040970,
			0.037262,
			0.033117,
			0.028535,
			0.023518,
			0.018064,
			0.012173,
			0.005847,
			-0.000916,
			-0.008116,
			-0.015751,
			-0.023823,
			-0.032331
		}),
		new SavitzkyGolayFilter(3, 12, 12, new double[]{  
			-0.048889,
			-0.026667,
			-0.006377,
			0.011981,
			0.028406,
			0.042899,
			0.055459,
			0.066087,
			0.074783,
			0.081546,
			0.086377,
			0.089275,
			0.090242,
			0.089275,
			0.086377,
			0.081546,
			0.074783,
			0.066087,
			0.055459,
			0.042899,
			0.028406,
			0.011981,
			-0.006377,
			-0.026667,
			-0.048889
		}),
		new SavitzkyGolayFilter(2, 25, 25, new double[]{
			-0.026637,
			-0.021088,
			-0.015765,
			-0.010668,
			-0.005799,
			-0.001155,
			0.003262,
			0.007452,
			0.011416,
			0.015153,
			0.018664,
			0.021948,
			0.025006,
			0.027838,
			0.030442,
			0.032821,
			0.034972,
			0.036898,
			0.038597,
			0.040069,
			0.041315,
			0.042334,
			0.043127,
			0.043693,
			0.044033,
			0.044146,
			0.044033,
			0.043693,
			0.043127,
			0.042334,
			0.041315,
			0.040069,
			0.038597,
			0.036898,
			0.034972,
			0.032821,
			0.030442,
			0.027838,
			0.025006,
			0.021948,
			0.018664,
			0.015153,
			0.011416,
			0.007452,
			0.003262,
			-0.001155,
			-0.005799,
			-0.010668,
			-0.015765,
			-0.021088,
			-0.026637,
		}),
		new SavitzkyGolayFilter(2, 20, 20, new double[]{
			-0.032331,
			-0.023823,
			-0.015751,
			-0.008116,
			-0.000916,
			0.005847,
			0.012173,
			0.018064,
			0.023518,
			0.028535,
			0.033117,
			0.037262,
			0.040970,
			0.044243,
			0.047079,
			0.049479,
			0.051442,
			0.052969,
			0.054060,
			0.054714,
			0.054933,
			0.054714,
			0.054060,
			0.052969,
			0.051442,
			0.049479,
			0.047079,
			0.044243,
			0.040970,
			0.037262,
			0.033117,
			0.028535,
			0.023518,
			0.018064,
			0.012173,
			0.005847,
			-0.000916,
			-0.008116,
			-0.015751,
			-0.023823,
			-0.032331,
		}),
		new SavitzkyGolayFilter(2, 15, 15, new double[]{
			-0.041056,
			-0.026393,
			-0.012741,
			-0.000101,
			0.011528,
			0.022146,
			0.031752,
			0.040348,
			0.047932,
			0.054505,
			0.060067,
			0.064617,
			0.068157,
			0.070685,
			0.072201,
			0.072707,
			0.072201,
			0.070685,
			0.068157,
			0.064617,
			0.060067,
			0.054505,
			0.047932,
			0.040348,
			0.031752,
			0.022146,
			0.011528,
			-0.000101,
			-0.012741,
			-0.026393,
			-0.041056,
		}),
		new SavitzkyGolayFilter(2, 10, 10, new double[]{
			-0.055901,
			-0.024845,
			0.002942,
			0.027460,
			0.048709,
			0.066688,
			0.081399,
			0.092841,
			0.101013,
			0.105917,
			0.107551,
			0.105917,
			0.101013,
			0.092841,
			0.081399,
			0.066688,
			0.048709,
			0.027460,
			0.002942,
			-0.024845,
			-0.055901,
		}),
		new SavitzkyGolayFilter(2, 5, 5, new double[]{
			-0.083916,
			0.020979,
			0.102564,
			0.160839,
			0.195804,
			0.207459,
			0.195804,
			0.160839,
			0.102564,
			0.020979,
			-0.083916,
		}),		
		
	// Taken from Numerical Recipes in C, p.651:  (maybe not very useful)
		new SavitzkyGolayFilter(2, 2, 2, new double[]{                         -0.086,  0.343,  0.486,  0.343, -0.086 }),
		new SavitzkyGolayFilter(2, 3, 1, new double[]{                 -0.143,  0.171,  0.343,  0.371,  0.257 }),
		new SavitzkyGolayFilter(2, 4, 0, new double[]{          0.086, -0.143, -0.086,  0.257,  0.886 }),
		new SavitzkyGolayFilter(2, 5, 5, new double[]{ -0.084,  0.021,  0.103,  0.161,  0.196,  0.207,  0.196,  0.161,  0.103,  0.021, -0.084 }),
		new SavitzkyGolayFilter(4, 4, 4, new double[]{          0.035, -0.128,  0.070,  0.315,  0.417,  0.315,  0.070, -0.128,  0.035 }),
		new SavitzkyGolayFilter(4, 5, 5, new double[]{  0.042, -0.105, -0.023,  0.140,  0.280,  0.333,  0.280,  0.140, -0.023, -0.105,  0.042 }),
	};
	

}
