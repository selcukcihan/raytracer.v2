/**
 * @date	: 28.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.vision.sampling
 */
package com.selcukcihan.raytracer.vision.sampling;

/**
 * @author selcuk
 *
 */
public class TrivialSampler implements ISampler
{
	private int m_w = 0;
	private int m_h = 0;
	private int m_wIndex = 0;
	private int m_hIndex = 0;
	public TrivialSampler(int p_w, int p_h)
	{
		m_w = p_w;
		m_h = p_h;
		m_wIndex = 0;
		m_hIndex = 0;
	}

	public Sample nextSample()
	{
		if(m_hIndex == m_h)
			return null;
		
		Sample retVal = new Sample();
		retVal.imgX = m_wIndex;
		retVal.imgY = m_hIndex;
		retVal.x = retVal.imgX - (m_w / 2.0f);
		retVal.y = retVal.imgY - (m_h / 2.0f);
		
		m_wIndex++;
		if(m_wIndex == m_w)
		{
			m_wIndex = 0;
			m_hIndex++;
		}
		
		return retVal;
	}

}
