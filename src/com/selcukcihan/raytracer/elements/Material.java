/**
 * @date	: 26.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import com.selcukcihan.raytracer.vision.Color;

/**
 * @author selcuk
 *
 */
public class Material
{
	public final Color AmbientColor;
	public final Color DiffuseK;
	public final Color SpecularK;
	public final double NS;

	public final Color Reflectivity;
	public final Color Refractivity;
	public final double RefractiveIndex;
	
	public Material()
	{
		AmbientColor = new Color(0.2f, 0.8f, 0.2f);
		DiffuseK = new Color(0.7);
		SpecularK = new Color(0.5);
		NS = 64;

		Reflectivity = new Color(0);
		Refractivity = new Color(0);
		RefractiveIndex = 1;
	}
	
	public Material(Color p_ambientColor, Color p_diffuseK, Color p_specularK, double p_ns,
			Color p_reflectivity, Color p_refractivity, double p_refractiveIndex)
	{
		AmbientColor = p_ambientColor;
		DiffuseK = p_diffuseK;
		SpecularK = p_specularK;
		NS = p_ns;
		
		Reflectivity = p_reflectivity;
		Refractivity = p_refractivity;
		RefractiveIndex = p_refractiveIndex;
	}

}
