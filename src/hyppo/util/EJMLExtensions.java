package hyppo.util;

import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.CommonOps_DDRM;
import org.ejml.simple.SimpleMatrix;

/**
 * Useful operations not native to EJML
 * @author Daniel
 *
 */
public class EJMLExtensions {
	
	/**
	 * @param rows
	 * @param cols
	 * @return a matrix of the specified shape filled with ones
	 */
	public static DMatrixRMaj ones(int rows, int cols) {
		SimpleMatrix m = SimpleMatrix.wrap(new DMatrixRMaj(rows, cols));
		m.set(1);
		return m.matrix_F64();
	}
	
	
	/**
	 * Generates the covariance matrix of the given data, and a bunch more stuff. 
	 * Only the necessary calculations are performed, so don't worry about performance.
	 * @param data the data from which to generate the cov matrix. Its 
	 * shape is supposed to be nxm, with n being the sample size, and m the number of samples.
	 * @param cov if not null, return the covariance here. Matrix is modified
	 * @param summ if not null, return the summation here. Matrix is modified
	 * @param mean if not null, return the mean here. Matrix is modified
	 */
	public static void generateCovarianceMatrix(DMatrixRMaj data, DMatrixRMaj cov, DMatrixRMaj summ, DMatrixRMaj mean) {
		if (cov == null && summ == null && mean == null) {
			return;
		}
		
		int dim = data.getNumRows();
		int samples = data.getNumCols();
		
		/** Summ is always required */
		if (summ == null) {
			summ = new DMatrixRMaj(dim);
		}	
		summ.reshape(dim, 1);
		CommonOps_DDRM.mult(data, EJMLExtensions.ones(samples, 1), summ);
        
        /** Mean is required if mean or cov are required */
        if (mean == null) {
        	if (cov == null) {
        		return;
        	}
        	mean = new DMatrixRMaj(dim);
        }
        mean.set(summ);
        CommonOps_DDRM.divide(mean, (double) data.getNumCols());
        
        /** Calculate cov if asked for */
        if (cov == null) {
        	return;
        }
        cov.reshape(dim, dim);
        CommonOps_DDRM.multTransB(data, data, cov);
        DMatrixRMaj s2 = new DMatrixRMaj(dim, dim);
        CommonOps_DDRM.multTransB(mean, summ, s2);
        CommonOps_DDRM.subtract(cov, s2, cov);
	}

	/**
	 * Does the inverse square root of all the elements in the diagonal. Use only with diagonal matrices
	 * @param source
	 */
	public static void inverseSquareRoot(DMatrixRMaj source) {
		if (source.getNumCols() != source.getNumRows()) {
			throw new IllegalArgumentException("Only works on square matrices");
		}
		
		for (int i = 0; i < source.getNumCols(); i++) {
			double val = source.get(i, i);
			if (Math.abs(val) < 0.1e-30) {
				source.set(i, i, 0);
			} else  {
				source.set(i, i, (double) (1.0 / Math.sqrt(val)));
			}
		}
		
	}
	
	
	/**
	 * Gets a subset of the given matrix
	 * @param source from where to take samples
	 * @param probability probability that one sample from source will make it to the 
	 * returned dataset. e.g: if probability = 0.1 -> 1 out of 10 samples will be output
	 * @return the subset of the input, which could be empty if <code>probability</code> 
	 * is too low
	 */
	public static DMatrixRMaj getSubSet(DMatrixRMaj source, double probability) {
		if (probability < 0 || probability > 1) {
			throw new IllegalArgumentException("Probability must be between 0 and 1");
		}
		if (probability == 1) {
			return source;
		}
		int samples = (int) (source.getNumCols() * probability);
		DMatrixRMaj result = new DMatrixRMaj(source.getNumRows(), samples);
		for (int i = 0; i < samples; i++) {
			double which = ((double) i) * source.getNumCols() / (double) samples;
			int index = (int) Math.round(which); //should be between 0 and source.numcols - 1
			for (int j = 0; j < source.getNumRows(); j++) {
				result.set(j, i, source.get(j, index));
			}
		}
		
		return result;
	}
	
	/**
	 * Substract the given <code>vector</code> from every column of the given <code>matrix</code>
	 * @param matrix
	 * @param vector
	 */
	public static void addColumnVector(DMatrixRMaj matrix, DMatrixRMaj vector) {
		if (vector.numCols != 1) {
			throw new IllegalArgumentException("the vector must have one column");
		}
		if (vector.numRows != matrix.numRows) {
			throw new IllegalArgumentException("the vector and matrix must have the same number of rows");
		}
		
		for (int i = 0; i < matrix.numRows; i++) {
			for (int j = 0; j < matrix.numCols; j++) {
				matrix.data[i*matrix.numCols + j] += vector.data[i];
			}
		}
	}
	
	
	/**
	 * Substract the given <code>vector</code> from every column of the given <code>matrix</code>
	 * @param matrix
	 * @param vector
	 */
	public static void subColumnVector(DMatrixRMaj matrix, DMatrixRMaj vector) {
		if (vector.numCols != 1) {
			throw new IllegalArgumentException("the vector must have one column");
		}
		if (vector.numRows != matrix.numRows) {
			throw new IllegalArgumentException("the vector and matrix must have the same number of rows");
		}
		
		for (int i = 0; i < matrix.numRows; i++) {
			for (int j = 0; j < matrix.numCols; j++) {
				matrix.data[i*matrix.numCols + j] -= vector.data[i];
			}
		}
	}

	/**
	 * @param h1
	 * @return a pair of integers, the firs one being the minimum value within the image, 
	 * the second one being the maximum. 
	 */
	public static double[] minMax(DMatrixRMaj h1) {
		double[] minMax = new double[2];
		minMax[0] = Integer.MAX_VALUE;
		minMax[1] = Integer.MIN_VALUE;
		for (int i = 0; i < h1.getNumRows(); i++) {
			for (int j = 0; j < h1.getNumCols(); j++) {
				double sample = h1.get(i, j);
				if (minMax[0] > sample) {
					minMax[0] = sample;
				}
				if (minMax[1] < sample) {
					minMax[1] = sample;
				}
			}
		}
		return minMax;
	}
	
	/**
	 * @param h1
	 * @return the average value of the samples in h1
	 */
	public static double avg(DMatrixRMaj h1) {
		SimpleMatrix m = SimpleMatrix.wrap(h1);
		return (double) (m.elementSum() / (double) m.getNumElements());
	}
	
	/**
	 * @param h1
	 * @param avg precalculated average for faster computation (if null will calculate)
	 * @return the variance of the samples of the given image
	 */
	public static double var(DMatrixRMaj h1, Double avg) {
		if (avg == null) {
			avg = avg(h1);
		}
		double acc = 0;
		for (int j = 0; j < h1.getNumRows(); j++) {
			for (int k = 0; k < h1.getNumCols(); k++) {
				double val = (double) h1.get(j, k) - avg;
				acc += val * val;
			}
		}
		return (double) (acc / (double) h1.getNumElements());
	}
	
	/**
	 * @param h1
	 * @param h2 
	 * @param avg1 average of h1 used for speed up. If not available send null
	 * @param avg2 average of h2 used for speed up. If not available send null
	 * @return the covariance of both images
	 */
	public static double cov(DMatrixRMaj h1, DMatrixRMaj h2, Double avg1, Double avg2) {
		ImageComparisons.checkDimensions(h1, h2);
		if (avg1 == null) {
			avg1 = avg(h1);
		}
		if (avg2 == null) {
			avg2 = avg(h2);
		}
		double acc = 0;
		for (int i = 0; i < h1.getNumRows(); i++) {
			for (int j = 0; j < h1.getNumCols(); j++) {
				acc += (h1.get(i, j) - avg1) * (h2.get(i, j) - avg2);
			}
		}

		return (double) (acc / (double) h1.getNumElements());
	}
	
	/**
	 * @param h1
	 * @param avg average of h1, used to speed up calculations. if null, it will be calculated
	 * @return the std of the samples of the given image
	 */
	public static double std(DMatrixRMaj h1, double avg) {
		return (double) Math.sqrt(var(h1, avg));
	}
	
	/**
	 * Calculate the mean difference of each row
	 * @param h1
	 * @param h2
	 * @return a matrix of size (h1.rows, 1) containing the mean diff of each row of h1 and h2
	 */
	public static DMatrixRMaj meanDiff(DMatrixRMaj h1, DMatrixRMaj h2) {
		ImageComparisons.checkDimensions(h1, h2);
		
		DMatrixRMaj res = new DMatrixRMaj(h1.getNumRows(), 1);
		
		for (int i = 0; i < h1.getNumRows(); i++) {
			for (int j = 0; j < h1.getNumCols(); j++) {
				res.add(i, 0, h1.get(i, j) - h2.get(i, j));
			}
		}
		
		CommonOps_DDRM.divide(res, h1.getNumCols());
		
		return res;
	}
	
	
	/**
	 * @param h1
	 * @return the power of the image (mean of the squared values)
	 */
	public static double power(DMatrixRMaj h1) {
		double acc = 0;
		for (int i = 0; i < h1.getNumRows(); i++) {
			for (int j = 0; j < h1.getNumCols(); j++) {
				acc += h1.get(i, j) * h1.get(i, j);
			}
		}
		acc /= h1.getNumElements();
		return (double) acc;
	}
}
