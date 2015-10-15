/**
 * @date	: 30.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import javax.media.j3d.Transform3D;

import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.vision.Intersection;
import com.selcukcihan.raytracer.vision.Ray;

/**
 * @author selcuk
 *
 */
public interface IGeometry
{
	public Intersection intersect(Ray p_ray, Transform3D p_xform, Drawable p_d);
	public boolean inside(Point3 p_point, Transform3D p_xform);
}
