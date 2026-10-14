
public class Color {
	private int red;
	private int green;
	private int blue;
	
	public static Color decode(String hex) {
		Color newColor = new Color();
		newColor.setRGB('R',Integer.parseInt(hex.charAt(2) + "",16) * 16 + Integer.parseInt(hex.charAt(3) + "",16));
		newColor.setRGB('G',Integer.parseInt(hex.charAt(4) + "",16) * 16 + Integer.parseInt(hex.charAt(5) + "",16));
		newColor.setRGB('B',Integer.parseInt(hex.charAt(6) + "",16) * 16 + Integer.parseInt(hex.charAt(7) + "",16));
		return newColor;
	}
	
	public static void RGBtoHSB(int red, int green, int blue, float hsbCode[]) {
		float max = Math.max(red, Math.max(green, blue));
		float min = Math.min(red, Math.min(green, blue));
	    
	    //Brightness
	    
		hsbCode[2] = max / 255;
		
	    //Saturation
	    
		hsbCode[1] = max != 0 ? (max - min) / max : 0;
		
		//Hue
		
	    if (hsbCode[1] == 0) {
	        hsbCode[0] = 0;
	    } else {
	        float diff = max - min;
	        float hue;
	        
	        if (red == max) {
	            hue = (green - blue) / diff;
	        } else if (green == max) {
	            hue = 2 + (blue - red) / diff; //adding 2 moves us to from red to green section
	        } else {
	            hue = 4 + (red - green) / diff; //adding 4 moves us from red to blue section
	        }
	        
	        hue /= 6; // turn it into range from 0.0 - 1.0
	        
	        if (hue < 0) {
	            hue += 1;
	        }
	        hsbCode[0] = hue;
	    }
		
	}
	
	public static void RGBtoHSL(int red, int green, int blue, float hslCode[]) {
		float redPercent = red / 255.0f;
		float greenPercent = green / 255.0f;
		float bluePercent = blue / 255.0f;
		float max = Math.max(redPercent, Math.max(greenPercent, bluePercent));
		float min = Math.min(redPercent, Math.min(greenPercent, bluePercent));
		float diff = max - min;
	    
	    //Lightness
	    
		hslCode[2] = (max + min) / 2;
		
	    //Saturation
	    
		hslCode[1] = hslCode[2] > 0.5 ? diff / (2 - max - min) : diff / (max + min);
		
		//Hue
		
	    if (hslCode[1] == 0) {
	        hslCode[0] = 0;
	    } else {
	        float hue;
	        
	        if (redPercent == max) {
	            hue = (greenPercent - bluePercent) / diff;
	        } else if (greenPercent == max) {
	            hue = 2 + (bluePercent - redPercent) / diff; //adding 2 moves us to from red to green section
	        } else {
	            hue = 4 + (redPercent - greenPercent) / diff; //adding 2 moves us to from red to blue section
	        }
	        
	        hue /= 6; // turn it into range from 0.0 - 1.0
	        
	        if (hue < 0) {
	            hue += 1;
	        }
	        hslCode[0] = hue;
	    }
		
	}
	
	public static void RGBtoCMYK(int red, int green, int blue, float cmyk[]) {
		float redPercent = red / 255.0f;
		float greenPercent = green / 255.0f;
		float bluePercent = blue / 255.0f;
		float max = Math.max(redPercent, Math.max(greenPercent, bluePercent));
	    
	    //K (Black)
	    
		cmyk[3] = 1 - max;
		
	    //RYK (Red, Green and Blue)
	    
        if (max == 0) { //prevent dividing by 0
            cmyk[0] = 0;
            cmyk[1] = 0;
            cmyk[2] = 0;
        }
        else {
        	cmyk[0] = (1.0f - redPercent - cmyk[3]) / max;
        	cmyk[1] = (1.0f - greenPercent - cmyk[3]) / max;
        	cmyk[2] = (1.0f - bluePercent - cmyk[3]) / max;
        }
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public int getRGB() {
		return (255 << 24) | (red << 16) | (green << 8) | blue;
	}
	
	private void setRGB(char name, int number) {
		if(name == 'R') {
			red = number;
		}
		else if(name == 'G') {
			green = number;
		}
		else  {
			blue = number;
		}
	}
}
