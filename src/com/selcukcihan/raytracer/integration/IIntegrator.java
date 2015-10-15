/**
 * @date	: 24.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.integration
 */
package com.selcukcihan.raytracer.integration;

import com.selcukcihan.raytracer.Scene;
import com.selcukcihan.raytracer.vision.Color;
import com.selcukcihan.raytracer.vision.Ray;
import com.selcukcihan.raytracer.vision.sampling.Sample;

/**
 * @author selcuk
 *
 */
public interface IIntegrator
{

	/**
	 * @param p_scene
	 * @param p_ray
	 * @param p_sample
	 * @return
	 */
	public Color computeIntensity(Scene p_scene, Ray p_ray, Sample p_sample);

}
