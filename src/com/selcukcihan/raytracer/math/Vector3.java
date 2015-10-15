package com.selcukcihan.raytracer.math;

import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public class Vector3 extends Vector3d
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2628217882764270370L;

	public Vector3()
	{
		super();
	}

	public Vector3(double[] p_arg0)
	{
		super(p_arg0);

	}

	public Vector3(Vector3d p_arg0)
	{
		super(p_arg0);

	}

	public Vector3(Vector3f p_arg0)
	{
		super(p_arg0);

	}

	public Vector3(Tuple3f p_arg0)
	{
		super(p_arg0);

	}

	public Vector3(Tuple3d p_arg0)
	{
		super(p_arg0);

	}

	public Vector3(double p_arg0, double p_arg1, double p_arg2)
	{
		super(p_arg0, p_arg1, p_arg2);

	}

	public Vector3 sub(Vector3 p_v) /* return (this - p_v) */
	{
		Vector3 retVal = new Vector3(this);
		retVal.sub((Tuple3d)p_v);
		return retVal;
	}
	
	public Vector3 add(Vector3 p_v) /* return (this + p_v) */
	{
		Vector3 retVal = new Vector3(this);
		retVal.add((Tuple3d)p_v);
		return retVal;
	}
	
	public Vector3 getNormalized()
	{
		Vector3 retVal = new Vector3(this);
		retVal.normalize();
		return retVal;
	}
	
	public Vector3 cross(Vector3 p_right) /* returns this crossed with p_right */
	{
		Vector3 retVal = new Vector3();
		retVal.cross(this, p_right);
		return retVal;
	}
	
	public Vector3 mul(double p_scale)
	{
		Vector3 retVal = new Vector3(this);
		retVal.scale(p_scale);
		return retVal;
	}
	
	public boolean isNaN()
	{
		return (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z));
	}
}
