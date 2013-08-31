package wa;

class Face {
	double[][] v;
	Face(double[][] v) {
		this.v = v;
	}
	Face(double[] v0, double[] v1, double[] v2, double[] v3) {
		v = new double[4][3];
		v[0] = v0;
		v[1] = v1;
		v[2] = v2;
		v[3] = v3;
	}
	Face set(double[][] v) {
		this.v = v;
		return this;
	}
	Face set(double[] v0, double[] v1, double[] v2, double[] v3) {
		v = new double[4][3];
		v[0] = v0;
		v[1] = v1;
		v[2] = v2;
		v[3] = v3;
		return this;
	}
	Face add(double[] v) {
		this.v[0][0] += v[0];this.v[0][1] += v[1];this.v[0][2] += v[2];
		this.v[1][0] += v[0];this.v[1][1] += v[1];this.v[1][2] += v[2];
		this.v[2][0] += v[0];this.v[2][1] += v[1];this.v[2][2] += v[2];
		this.v[3][0] += v[0];this.v[3][1] += v[1];this.v[3][2] += v[2];
		return this;
	}
	Face add(double x, double y, double z) {
		this.v[0][0] += x;this.v[0][1] += y;this.v[0][2] += z;
		this.v[1][0] += x;this.v[1][1] += y;this.v[1][2] += z;
		this.v[2][0] += x;this.v[2][1] += y;this.v[2][2] += z;
		this.v[3][0] += x;this.v[3][1] += y;this.v[3][2] += z;
		return this;
	}
	Face rotate() {
		// 0 -1
		// 1 0
		double[][] n = {
				{-v[0][2], v[0][1], v[0][0]},
				{-v[1][2], v[1][1], v[1][0]},
				{-v[2][2], v[2][1], v[2][0]},
				{-v[3][2], v[3][1], v[3][0]} };
		set(n).add(1.0F, 0.0F, 0.0F);
		return this;
	}

	Face rotate(int n) {
		if(n == 90) {
			return rotate();
		}
		if(n == 180) {
			return rotate().rotate();
		}
		if(n == 270) {
			return rotate().rotate().rotate();
		}
		return this;
	}
}
