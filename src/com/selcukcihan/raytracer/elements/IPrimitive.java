/**
 * @date	: 24.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import com.selcukcihan.raytracer.vision.Intersection;
import com.selcukcihan.raytracer.vision.Ray;

/**
 * @author selcuk
 *
 */
public interface IPrimitive
{

	/**
	 * @param p_ray
	 * @return
	 */
	Intersection intersect(Ray p_ray);

}
