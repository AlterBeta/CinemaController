package uzv.alter.cinema.common.hall;

import uzv.alter.cinema.common.seat.ISeatable;

import java.util.List;

public class Hall {
    private int[][] seatMatrix;
    private List<ISeatable> idToSeat;

    public Hall(int[][] matrix, List<ISeatable> idToSeat) {
        this.seatMatrix = matrix;
        this.idToSeat = idToSeat;
    }


    public int getWidth() {
        return this.seatMatrix[0].length;
    }

    public int getHeight() {
        return this.seatMatrix.length;
    }

    public ISeatable getSeat(int x, int y) {
        return idToSeat.get(seatMatrix[x][y]);
    }

    public int[][] getLayout() {
        return seatMatrix;
    }

    public List<ISeatable> getSeatTypes() {
        return this.idToSeat;
    }
}
