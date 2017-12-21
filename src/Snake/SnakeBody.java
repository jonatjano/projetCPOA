package Snake;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;

/**
 * classe corps du serpent, suis le corps précedant à la perfection
 * @author Jonathan Selle, Adam Bernouy
 *
 */
public class SnakeBody extends SnakePart {

	private List<Double> lastSpeeds;
	private List<Transform3D> lastRotations;

	private int startingCounter;
	private static int count = 0;

	public SnakeBody(Vector3d position, Snake snake) {
		super(position, "body" + count++, snake);
		resetValues();
	}

	public void performBehavior() {
		super.performBehavior();

		if (getLinked() != null) {
			lastSpeeds.add(getLinked().getTranslationalVelocity());
			Transform3D rot = new Transform3D();
			getLinked().getRotationTransform(rot);
			lastRotations.add(rot);

			if (isAwayFromLinked() && !startedToMove) {
				startedToMove = true;
			}

			if (startedToMove) {
				
				setTranslationalVelocity(lastSpeeds.remove(0));
				rotateY(lastRotations.remove(0));
			}
		}
	}

	void resetValues(SnakePart link) {
		setPartLink(link);
		resetValues();
	}

	void resetValues() {
		startingCounter = getCounter();
		startedToMove = false;

		lastSpeeds = new ArrayList<Double>();
		lastRotations = new ArrayList<Transform3D>();

		setTranslationalVelocity(0);
		setRotationalVelocity(0);
	}

	static void resetCounter() {
		count = 0;
	}
}