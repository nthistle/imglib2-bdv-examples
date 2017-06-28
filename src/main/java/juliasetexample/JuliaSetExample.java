package juliasetexample;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.scijava.ui.behaviour.InputTrigger;

import bdv.util.Bdv;
import bdv.util.BdvFunctions;
import bdv.util.BdvHandle;
import bdv.util.BdvOptions;
import net.imglib2.converter.Converters;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.util.Intervals;

public class JuliaSetExample
{
	public static void main( String[] args )
	{
		final JuliaFunction fractals = new JuliaFunction();

		JuliaConverter conv = new JuliaConverter( ColorScheme.BLACKWHITE );

		final Bdv b = BdvFunctions.show( Converters.convert( fractals, conv, new ARGBType() ), Intervals.createMinMax( -200, -200, 200, 200 ), "julia",
				new BdvOptions().numRenderingThreads( 2 ).is2D() );

		final BdvHandle bhandle = b.getBdvHandle();

		bhandle.getSetupAssignments().getMinMaxGroups().get( 0 ).setRange( 0, 255 );

		configureBindings( bhandle, fractals, conv );
	}

	public static void configureBindings( final BdvHandle bhandle, final JuliaFunction fractals, JuliaConverter conv )
	{

		bhandle.getKeybindings().getConcatenatedInputMap().put( KeyStroke.getKeyStroke( "F2" ), "increaseIts" );
		bhandle.getKeybindings().getConcatenatedActionMap().put( "increaseIts", new IncreaseIterations( fractals, bhandle ) );

		bhandle.getKeybindings().getConcatenatedInputMap().put( KeyStroke.getKeyStroke( "M" ), "toggleMandel" );
		bhandle.getKeybindings().getConcatenatedActionMap().put( "toggleMandel", new AbstractAction()
		{
			public void actionPerformed( ActionEvent arg0 )
			{
				fractals.toggleMandelbrot();
				bhandle.getViewerPanel().requestRepaint();
			}
		} );

		bhandle.getKeybindings().getConcatenatedInputMap().put( KeyStroke.getKeyStroke( "1" ), "setScheme_1" );
		bhandle.getKeybindings().getConcatenatedActionMap().put( "setScheme_1",
				new ColorSchemeAction( conv, bhandle, ColorScheme.BLACKWHITE ) );

		bhandle.getKeybindings().getConcatenatedInputMap().put( KeyStroke.getKeyStroke( "2" ), "setScheme_2" );
		bhandle.getKeybindings().getConcatenatedActionMap().put( "setScheme_2",
				new ColorSchemeAction( conv, bhandle, ColorScheme.RAINBOW ) );

		bhandle.getKeybindings().getConcatenatedInputMap().put( KeyStroke.getKeyStroke( "3" ), "setScheme_3" );
		bhandle.getKeybindings().getConcatenatedActionMap().put( "setScheme_3",
				new ColorSchemeAction( conv, bhandle, ColorScheme.DARKGREEN ) );

		bhandle.getKeybindings().getConcatenatedInputMap().put( KeyStroke.getKeyStroke( "4" ), "setScheme_4" );
		bhandle.getKeybindings().getConcatenatedActionMap().put( "setScheme_4",
				new ColorSchemeAction( conv, bhandle, ColorScheme.WARMTH ) );

		bhandle.getTriggerbindings().getConcatenatedInputTriggerMap().put( InputTrigger.getFromString( "C" ), "changeC" );
		bhandle.getTriggerbindings().getConcatenatedBehaviourMap().put( "changeC", new JuliaMouse( fractals, bhandle ) );
	}
}
