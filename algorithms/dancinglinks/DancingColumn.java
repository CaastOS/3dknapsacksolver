package algorithms.dancinglinks;

/* References:
    https://github.com/warren/algorithm-x/blob/master/DancingLinks.java
    https://www.ocf.berkeley.edu/~jchu/publicportal/sudoku/sudoku.paper.html
    https://stackoverflow.com/questions/54403754/knuth-dancing-links-with-secondary-columns
*/

class DancingColumn extends DancingNode { // this Object represents a single column
    int size; // 1s in the column
    String name;


    DancingColumn() {
        super(); // Inherit from DancingNode
        this.header = this;
        this.size = 0;
        this.name = "";
    }

    /**
     * unlink from row and col
     */

    void unlink() {
        this.unlinkFromRow();

        for (DancingNode i = this.d; i != this.header; i = i.d) {
            for (DancingNode j = i.r; j != i; j = j.r) {
                j.unlinkFromColumn();
            }
        }
    }

    /**
     * link to row and col
     */

    void link() {
        for (DancingNode i = this.u; i != this.header; i = i.u) {
            for (DancingNode j = i.l; j != i; j = j.l) {
                j.relinkToColumn();
            }
        }
        this.relinkToRow();
    }
}