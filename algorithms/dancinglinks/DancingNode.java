package algorithms.dancinglinks;
/* References:
    https://github.com/warren/algorithm-x/blob/master/DancingLinks.java
    https://www.ocf.berkeley.edu/~jchu/publicportal/sudoku/sudoku.paper.html
    https://stackoverflow.com/questions/54403754/knuth-dancing-links-with-secondary-columns
*/

class DancingNode { // This class represents a dancing link

    DancingNode l, r, u, d; // l, right, up, down
    DancingColumn header; // column header
    int inputRow; // input row

    DancingNode() {
        l = r = u = d = this;
        inputRow = -1;
    }

    DancingNode(int inputRow) {
        l = r = u = d = this;
        this.inputRow = inputRow;
    }

    /**
     * link node down
     * @param node node to link
     * @return linked node
     */

    public DancingNode linkDown(DancingNode node) {
        node.d = d;
        node.d.u = node;
        node.u = this;
        d = node;
        return node;
    }

    /**
     * link node to right
     * @param node node to link
     * @return linked node
     */

    public DancingNode linkRight(DancingNode node) {
        node.r = r;
        node.r.l = node;
        node.l = this;
        r = node;
        return node;
    }

    /**
     * unlink from row
     */

    void unlinkFromRow() {
        this.l.r = this.r;
        this.r.l = this.l;
    }

    /**
     * link to row
     */

    void relinkToRow() {
        this.l.r = this.r.l = this;
    }


    /**
     * unlink from col
     */

    void unlinkFromColumn() {
        this.u.d = this.d;
        this.d.u = this.u;
        this.header.size--;
    }

    /**
     * link to col
     */

    void relinkToColumn() {
        this.u.d = this.d.u = this;
        this.header.size++;
    }
}