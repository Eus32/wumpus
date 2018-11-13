package org.eus.wumpus.utils;

import lombok.Data;
import lombok.NonNull;

@Data
public class Position {
	@NonNull private int positionX;
	@NonNull private int positionY;
}
