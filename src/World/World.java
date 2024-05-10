package World;

import World.organisms.Organism;

public class World {
    //map[human->getY()][human->getX()]->org = human;
    // zrobić klasę map z arg short[] i organism
    Map map;
    short width, height;
    boolean isHex = false;
    public World(short w, short h){
        width = (short) (w + 2);
        height = (short) (h + 2);
        map = new Map(width, height);
    }

    // zastanowić się nad tym jak to lepiej zrobić
    // bo tej metody będę używał w Antylopie, roślinach, zwierzętach itp.
    public short[] checkCellsAround(short[] position) {
        short y = position[0], x = position[1];
        if(!isHex){
            for (short i = -1; i < 2; ++i) {
                for (short j = -1; j < 2; ++j) {
                    if (i == 0 && j == 0) continue;
                    if ((x + j <= width - 2 && x + j > 0) && (y + i <= height - 2 && y + i > 0)) {
                        Organism organism = map.getOrganism(new short[]{(short) (y + i), (short) (x + j)});
                        if (organism == null) {
                            x += j;
                            y += i;
                            return new short[]{y, x};
                        }
                    }
                }
            }
        }

        else{
            short dLines[] = { -1, 0, 1, 1, 0, -1 };
            short dPoses[] = { -1, -1, 0, 1, 1, 0 };

            for (short i = 0; i < 6; ++i) {
                short newRow = (short)(y + dLines[i]);
                short newCol = (short)(x + dPoses[i]);
                if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < height) {
                    if(true){
                        break;
                    }
                }
            }
        }
        return new short[]{-1,-1};
    }
}
