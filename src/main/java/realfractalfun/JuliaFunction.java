package realfractalfun;

import net.imglib2.RealInterval;
import net.imglib2.RealPoint;
import net.imglib2.RealRandomAccess;
import net.imglib2.RealRandomAccessible;
import net.imglib2.type.numeric.real.DoubleType;

public class JuliaFunction extends RealPoint implements RealRandomAccess< DoubleType >, RealRandomAccessible< DoubleType >
{

	protected DoubleType t = new DoubleType();

	protected int numIts;

	protected double myRealC;

	protected double myImagC;

	protected boolean isMandelbrot = false;

	public JuliaFunction()
	{
		super( 2 );
		numIts = 100;
		myRealC = 0.28;
		myImagC = 0.008;
	}

	public JuliaFunction( int startingIts, double startRealC, double startImagC, boolean isMandel )
	{
		super( 2 );
		numIts = startingIts;
		myRealC = startRealC;
		myImagC = startImagC;
		isMandelbrot = isMandel;
	}

	public RealRandomAccess< DoubleType > realRandomAccess()
	{
		return copy();
	}

	public RealRandomAccess< DoubleType > realRandomAccess( RealInterval arg0 )
	{
		return copy();
	}

	public JuliaFunction copy()
	{
		return new JuliaFunction( this.numIts, this.myRealC, this.myImagC, this.isMandelbrot );
	}

	public void increaseIts( final int dIts )
	{
		this.numIts += dIts;
	}

	public void updateC( final double realPart, final double imagPart )
	{
		myRealC = realPart;
		myImagC = imagPart;
	}

	public void toggleMandelbrot()
	{
		isMandelbrot = !isMandelbrot;
	}

	public synchronized DoubleType get()
	{
		double realPart = position[ 0 ] / 100.0;
		double imagPart = position[ 1 ] / 100.0;
		if ( isMandelbrot )
		{
			myRealC = realPart;
			myImagC = imagPart;
		}
		double realPartN, imagPartN;
		for ( int i = 0; i < numIts; i++ )
		{
			if ( ( realPart * realPart ) + ( imagPart * imagPart ) > 4.0 )
			{
				t.set( ( ( double ) ( i + 1 ) / ( numIts + 1 ) ) );
				return t;
			}
			realPartN = realPart * realPart - imagPart * imagPart + myRealC;
			imagPartN = 2.0 * realPart * imagPart + myImagC;
			realPart = realPartN;
			imagPart = imagPartN;
		}
		t.set( 0.0 );
		return t;
	}

	public synchronized JuliaFunction copyRealRandomAccess()
	{
		return copy();
	}
}
