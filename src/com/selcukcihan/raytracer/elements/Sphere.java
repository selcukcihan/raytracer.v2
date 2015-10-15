/**
 * @date	: 30.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import javax.media.j3d.Transform3D;

import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.math.Vector3;
import com.selcukcihan.raytracer.vision.Intersection;
import com.selcukcihan.raytracer.vision.Ray;
import com.selcukcihan.raytracer.vision.Ray.RayType;

/**
 * @author selcuk
 *
 */
public class Sphere implements IGeometry
{
	private double m_r = 0; 
	public Sphere(double p_r)
	{
		m_r = p_r;
	}

	public Intersection intersect(Ray p_ray, Transform3D p_xform, Drawable p_d)
	{
		/*
		 * p_ray ile bu geometrinin p_xform uygulanmýþ halini kesiþtiriyoruz
		 * drawableyi de oluþturduðumuz Intersection objesinin içine koyuyoruz
		 * 
		 * 
		 * 
		 * Sphere intersection:
		 * assume sphere at center pc(xc, yc, zc), with radius r.
		 * assume ray with orign p0(x0, y0, z0) with direction d(dx, dy, dz)
		 * 
		 * sphere is defined as: (p-pc)(dot)(p-pc) = r^2
		 * and plugging in p = p0 + dt into sphere equation we get
		 * (dt + p0 - pc)(dot)(dt + p0 - pc) - r^2 = 0
		 * this equals
		 * d(dot)dt^2 + 2d(dot)(p0 - pc)t + (p0 - pc)(dot)(p0 - pc) - r^2 = 0
		 * 
		 * sign discriminant of this equation determines whether there is an intersection or not
		 */
		//if(inside(p_ray.point(), p_xform))
		//	assert(p_ray.Type == RayType.REFRACTED) : "nokta içeride, refracted ray olmak zorunda";

		Intersection retVal = null;
		Point3 isectPoint = null;
		Vector3 isectPointNormal = null;
		
		Vector3 d = p_ray.direction(); /* incoming ray direction */
		Point3 p0 = p_ray.point(); /* incoming ray's beginning point */
		
		//if(d.isNaN() || p0.isNaN())
		//	return null;
		
		Point3 pc = new Point3(0, 0, 0);
		p_xform.transform(pc); /* center of the sphere */
		
		Vector3 p0_minus_pc = new Vector3(p0.sub(pc));
		
		double r = m_r; /* ismi güzel olsun diye */
		
		//double a = 1; /* in fact a = d(dot)d but d is normalized, so no need to compute */
		double b = 2 * d.dot(p0_minus_pc);
		double c = p0_minus_pc.dot(p0_minus_pc) - (r * r);
		
		double disc = (b * b) - (4 * c); /* 4ac yerine 4c yazabildik a zaten 1'e eþit */
		if(disc < 0) /* no intersection */
			return null;
		else if(disc == 0) /* çift katlý kök var, teðet geçiyor */
		{
			return null;
		}
		else /* iki farkli reel kök var */
		{
			double t0 = (double) ((-b - Math.sqrt(disc)) / 2);
			double t1 = (double) ((-b + Math.sqrt(disc)) / 2);
			if(t1 <= 0) /* ok both roots are negative, that means object is behind the ray */
				return null;
			
			if(t0 >= 0) /* two intersections */
			{
				/*
				 * for a shadow ray two intersections means that light is occluded by this object (p_d)
				 * ray stems from the eye and intersects with p_d in two points
				 * in either case the intersection point can be safely returned as t0
				 */
				isectPoint = new Point3(d);
				//isectPoint.scaleAdd(t0 - Ray.EPSILON, p0);
				isectPoint.scaleAdd(t0, p0);
				isectPointNormal = (new Vector3(isectPoint.sub(pc))).getNormalized();
			}
			else /* one intersection, which is t1 */
			{
				//assert(p_ray.Type == RayType.REFRACTED) : "tek intersection olmasi halinde refraction olmak zorunda";
				
				isectPoint = new Point3(d);
				isectPoint.scaleAdd(t1, p0);
				isectPointNormal = (new Vector3(isectPoint.sub(pc))).getNormalized();
			}
		}
		
		isectPointNormal = (new Vector3(isectPoint.sub(pc))).getNormalized();
		retVal = new Intersection(isectPoint, isectPointNormal, p_d);
		return retVal;
	}

	public boolean inside(Point3 p_point, Transform3D p_xform)
	{
		Point3 pc = new Point3(0, 0, 0);
		p_xform.transform(pc); /* center of the sphere */
		return (pc.distanceSquared(p_point) < (m_r * m_r));
	}
}
