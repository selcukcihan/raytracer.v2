/**
 * @date	: 26.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import com.selcukcihan.raytracer.math.Vector3;
import com.selcukcihan.raytracer.vision.Intersection;
import com.selcukcihan.raytracer.vision.Ray;
import com.selcukcihan.raytracer.vision.Ray.RayType;

/**
 * @author selcuk
 *
 */
public class Drawable extends Element implements IPrimitive
{
	private Material m_mat = null;
	private IGeometry m_geo = null;

	public Drawable(IGeometry p_geo, Material p_mat)
	{
		super(); /* xform initialize ediliyor */
		
		m_mat = p_mat;
		m_geo = p_geo;
	}
	
	public Material getMaterial() { return m_mat; }

	public Intersection intersect(Ray p_ray)
	{
		Intersection retVal = m_geo.intersect(p_ray, super.m_xform, this);
		return retVal;
	}
	
	public Ray reflectedRay(Ray p_incoming, Intersection p_isect)
	{ /* compute reflection of the ray which arrives at this intersection */
		
		Vector3 incoming = new Vector3(p_incoming.direction());
		Vector3 normal = new Vector3(p_isect.normal());
		normal.scale((2 * incoming.dot(normal)));
		Vector3 d = incoming.sub(normal);
		
		return new Ray(p_isect.point().shift(Ray.EPSILON, d), d, RayType.REFLECTED);
		//return new Ray(p_isect.point(), d, RayType.REFLECTED);
	}
	
	public Vector3 reflectedRay(Vector3 p_incoming, Intersection p_isect)
	{
		/*
		 * burada p_incoming gelen rayin yönü, return edilen deðer de yansýyacak olan rayin yönü
		 */
		Vector3 incoming = new Vector3(p_incoming);
		Vector3 normal = new Vector3(p_isect.normal());
		normal.scale((2 * incoming.dot(normal)));
		Vector3 d = incoming.sub(normal);
		return d;
	}
	
	private Ray internalRefractedRay(Ray p_incoming, Intersection p_isect)
	{
		double n1 = m_mat.RefractiveIndex; /* cismin refractive indexi */
		double n2 = 1; /* gelen rayin oldugu yerdeki refractive index */
		Vector3 n = p_isect.normal().mul(-1);
		
		double sinAlpha = n.cross(p_incoming.direction()).length(); /* gelen ýþýnla normalin yaptýðý açýnýn sinüsü */
		double sinBeta = n1 * sinAlpha / n2; /* kýrýlan ýþýnla -normal arasýndaki açýnýn sinüsü */
		
		if(sinBeta >= 1)
			return null;

		double cosBeta = Math.sqrt(1 - (sinBeta * sinBeta)); /* kýrýlan ýþýnla -normal arasýndaki açýnýn kosinüsü */
		double cosAlpha = Math.sqrt(1 - (sinAlpha * sinAlpha)); /* gelen ýþýnla normal arasýndaki açýnýn kosinüsü */
		double m = 1 / (cosAlpha - (cosBeta * (n2 / n1)));
		
		Vector3 d = p_incoming.direction().mul(m).add(n).getNormalized();
		Ray r = new Ray(p_isect.point().shift(Ray.EPSILON, d), d, RayType.REFRACTED);
		
		return r;
	}

	public Ray refractedRay(Ray p_incoming, Intersection p_isect)
	{
		if((p_incoming.direction().dot(new Vector3(0, 0, -1))) > 0.99)
		{
			if((p_isect.normal().dot(new Vector3(0, 0, 1))) > 0.99)
				p_incoming.direction();
		}
		double n1 = 1; /* gelen rayin oldugu yerdeki refractive index */
		double n2 = m_mat.RefractiveIndex; /* cismin refractive indexi */
		Vector3 n = p_isect.normal();
		
		double sinAlpha = n.cross(p_incoming.direction()).length(); /* gelen ýþýnla normalin yaptýðý açýnýn sinüsü */
		double sinBeta = n1 * sinAlpha / n2; /* kýrýlan ýþýnla -normalin arasýndaki açýnýn sinüsü */
		
		if(sinBeta >= 1)
			return null;
		
		double cosBeta = Math.sqrt(1 - (sinBeta * sinBeta)); /* kýrýlan ýþýnla -normal arasýndaki açýnýn kosinüsü */
		double m = 1 / ((n2 / n1) * cosBeta + p_incoming.direction().dot(n));
		
		Vector3 d = p_incoming.direction().mul(m).sub(n).getNormalized();
		Ray r = new Ray(p_isect.point().shift(Ray.EPSILON, d), d, RayType.REFRACTED);
		Intersection isect = intersect(r);
		if(isect == null)
			return null;
		return internalRefractedRay(r, isect);
	}
	
//	public Vector3 refractedRay(Vector3 p_incoming, Intersection p_isect)
//	{
//		double n1 = 1; /* gelen rayin oldugu yerdeki refractive index */
//		double n2 = m_mat.RefractiveIndex; /* cismin refractive indexi */
//		double sinAlpha = p_isect.normal().cross(p_incoming).length(); /* gelen ýþýnla normalin yaptýðý açýnýn sinüsü */
//		double sinBeta = n1 * sinAlpha / n2; /* gelen ýþýnla normalin yaptýðý açýnýn kosinüsü */
//		double cosBeta = Math.sqrt(1 - (sinBeta * sinBeta)); /* kýrýlan ýþýnla -normal arasýndaki açýnýn kosinüsü */
//		double m = 1 / ((n2 / n1) * cosBeta + p_incoming.dot(p_isect.normal()));
//		
//		return p_incoming.mul(m).sub(p_isect.normal()).getNormalized();
//	}
}
