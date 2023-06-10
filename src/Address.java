public class Address {
    private int tag;
    private int index;
    private int line_num;

    public Address(int tag, int index, int line_num)
    {
        this.tag = tag;
        this.index = index;
        this.line_num = line_num;
    }

    public int getIndex() {
        return index;
    }

    public int getLine_num() {
        return line_num;
    }

    public int getTag() {
        return tag;
    }
}
