/**
 * 
 */
package Snake;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


/**
 * @author jonathan
 */
public class SnakeHeadIA extends SnakeHead
{
	private static float delta;
	private static boolean[][] occupedPositions;
	private static int lastCounter = -1;

	public SnakeHeadIA(Vector3d position, Snake snake)
	{
		super(position, snake);

		if (occupedPositions == null)
		{
			delta = radius;
			occupedPositions = new boolean[(int) (getSnake().getEnv().worldSize / delta)][(int) (getSnake().getEnv().worldSize / delta)];
		}
	}

	private void reloadTable()
	{
		if (getCounter() == lastCounter)
			return;

		lastCounter = getCounter();

		for (int i = 0; i < occupedPositions.length; i++)
		{
			for (int j = 0; j < occupedPositions[i].length; j++)
			{
				occupedPositions[i][j] = false;
			}
		}

		for (SnakePart sp : Snake.getListPart())
		{
			Point3d p = new Point3d();
			sp.getCoords(p);

			int caseX = (int) (p.x / delta);
			int caseY = (int) (p.y / delta);
			for (int i = caseX - 1; i <= caseX + 1; i++)
			{
				for (int j = caseY - 1; j <= caseY + 1; j++)
				{
					occupedPositions[i][j] = true;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see Snake.SnakeHead#behavior()
	 */
	@Override
	public void behavior()
	{
		//reloadTable();

		if ((getCounter() % 100) == 0)
		{
			rotateY(-Math.PI / 2);
		}
		
		
	}

//	void dijkstra(Sommet s)
//	{
//		for (Sommet si : listSommet)
//		{
//			si.estTraite(false);
//			si.precedent(s);
//			si.cout(100000000);
//		}
//		
//		s.estTraite(true);
//		s.cout(0);
//		
//		while (sommetNonTraite.size() > 0)
//		{
//			for Sommet si : sommetNonTraite
//			{
//				coutMin = si.cout;
//			}
//			
//			si.estTraite(true);
//			
//			for Sommet sj : sommetNonTraite
//			{
//				if si.cout + 1 < sj.cout
//				{
//					sj.predecesseur = si;
//					sj.cout = si.cout + 1;
//				}
//			}
//		}
//	}
}
