package juliasetexample;

import java.awt.Color;

import net.imglib2.converter.Converter;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.type.numeric.real.DoubleType;

public class JuliaConverter implements Converter< DoubleType, ARGBType >
{
	protected ColorScheme myScheme;

	public JuliaConverter( ColorScheme startScheme )
	{
		myScheme = startScheme;
	}

	public void setScheme( ColorScheme newScheme )
	{
		myScheme = newScheme;
	}

	@Override
	public void convert( DoubleType input, ARGBType output )
	{
		double val = input.get();
		switch ( myScheme )
		{
		case BLACKWHITE:
			int bwrgbval = ( int ) ( 255.0 * Math.sqrt( val ) );
			output.set( ARGBType.rgba( bwrgbval, bwrgbval, bwrgbval, 255 ) );

			break;

		case RAINBOW:
			if ( input.get() == 0 )
				output.set( ARGBType.rgba( 255, 255, 255, 255 ) );
			else
				output.set( Color.HSBtoRGB( ( float ) ( 0.95f + 2.0f * input.get() ), 0.6f, 1.0f ) );

			break;
			
		case DARKRAINBOW:
			if ( input.get() == 0 )
				output.set( ARGBType.rgba( 255, 255, 255, 255 ) );
			else
				output.set( Color.HSBtoRGB( ( float ) ( 0.95f + 2.0f * input.get() ), 0.7f, (float)(1.0f - input.get()*0.5)) );

			break;

		case DARKGREEN:
			int dgrgbval = ( int ) ( 255.0 * Math.sqrt( val ) );
			output.set( ARGBType.rgba( 0, dgrgbval, 0, 255 ) );

			break;

		case WARMTH:
			if ( input.get() == 0 )
				output.set( ARGBType.rgba( 0, 0, 0, 255 ) );
			else
			{
				double offset = 0.1;
				double wrval = 255.0 / ( 1 + ( 3.0 * Math.exp( -10.0 * ( val + offset ) ) ) );
				double wgval = 255.0 / ( 1 + ( 70.0 * Math.exp( -6.0 * ( val + offset ) ) ) );
				double wbval = 255.0 / ( 1 + ( 0.9 * Math.exp( 10.0 * ( val + offset ) ) ) );
				output.set( ARGBType.rgba( ( int ) wrval, ( int ) wgval, ( int ) wbval, 255 ) );
			}

			break;

		default:
			break;
		}
	}
}
