import java.util.HashMap;

abstract class Instruction {
    public abstract void execute();

} class and extends Instruction {
    private String rd;
    private String rs;
    private String rt;
    private HashMap<String,Integer> regs;
    public and(HashMap<String,Integer> regs,String rd, String rs, String rt){
        this.rd = rd;
        this.rs = rs;
        this.rt = rt;
        this.regs = regs;
    }
    public void execute(){
        regs.put(rd,(regs.get(rs)&regs.get(rt)));
    }
} class or extends Instruction {
    private String rd;
    private String rs;
    private String rt;
    private HashMap<String,Integer> regs;
    public or(HashMap<String,Integer> regs,String rd, String rs, String rt){
        this.rd = rd;
        this.rs = rs;
        this.rt = rt;
        this.regs = regs;
    }
    public void execute(){
        regs.put(rd,(regs.get(rs)|regs.get(rt)));
    }
} class add extends Instruction{
    private String rd;
    private String rs;
    private String rt;
    private HashMap<String,Integer> regs;
    public add(HashMap<String,Integer> regs,String rd, String rs, String rt){
        this.rd = rd;
        this.rs = rs;
        this.rt = rt;
        this.regs = regs;
    }
    public void execute(){

        regs.put(rd,(regs.get(rs)+regs.get(rt)));
    }

} class addi extends Instruction{
    private String rd;
    private String rs;
    private Integer imm;
    private HashMap<String,Integer> regs;
    public addi(HashMap<String,Integer> regs,String rd, String rs, Integer imm){
        this.rd = rd;
        this.rs = rs;
        this.imm = imm;
        this.regs = regs;
    }

    public void execute(){

        int reg = regs.get(rs).intValue();


        regs.put(rd, reg+imm);
    }

} class sll extends Instruction{
    private String rd;
    private String rs;
    private Integer shamt;

    private HashMap<String,Integer> regs;
    public sll(HashMap<String,Integer> regs,String rd, String rs, Integer shamt){
        this.rd = rd;
        this.rs = rs;
        this.shamt = shamt;
        this.regs = regs;
    }
    public void execute(){
        regs.put(rd,regs.get(rs)<<shamt);
    }

}class sub extends Instruction{
    private String rd;
    private String rs;
    private String rt;
    private HashMap<String,Integer> regs;
    public sub(HashMap<String,Integer> regs,String rd, String rs, String rt){
        this.rd = rd;
        this.rs = rs;
        this.rt = rt;
        this.regs = regs;
    }
    public void execute(){
        regs.put(rd,regs.get(rs)-regs.get(rt));
    }

}class slt extends Instruction{
    private String rd;
    private String rs;
    private String rt;
    private HashMap<String,Integer> regs;
    public slt(HashMap<String,Integer> regs,String rd, String rs, String rt){
        this.rd = rd;
        this.rs = rs;
        this.rt = rt;
        this.regs = regs;
    }
    public void execute(){
        if(regs.get(rs)<regs.get(rt)){
            regs.put(rd,1);
        } else {
            regs.put(rd,0);
        }
    }

}class beq extends Instruction{
    private String rs;
    private String rt;
    private Integer imm;
    private HashMap<String,Integer> regs;
    private int taken;
    public beq(HashMap<String,Integer> regs,String rs, String rt, Integer imm){
        this.rs = rs;
        this.rt = rt;
        this.imm = imm;
        this.regs = regs;
    }
    public void execute(){
        if(regs.get(rs) == regs.get(rt)){
            regs.put("pc",regs.get("pc")+imm);
            taken = 1;
        } else{
            taken = -1;
        }
    }
    public int isTaken(){
        return taken;
    }

}class bne extends Instruction{
    private String rs;
    private String rt;
    private Integer imm;
    private HashMap<String,Integer> regs;
    private int taken = 0;
    public bne(HashMap<String,Integer> regs, String rs, String rt, Integer imm){
        this.rs = rs;
        this.rt = rt;
        this.imm = imm;
        this.regs = regs;
    }
    public void execute(){
        if(regs.get(rs) != regs.get(rt)){
            regs.put("pc",regs.get("pc")+imm);
            taken = 1;
        } else{
            taken = -1;
        }
    }
    public int isTaken(){
        return taken;
    }

}class lw extends Instruction{
    private String rd;
    private Integer imm;
    private String addreg;
    private int[] memory;

    public String getRd() {
        return rd;
    }

    private HashMap<String,Integer> regs;
    public lw(HashMap<String,Integer> regs,int[] memory,String rd, Integer imm, String addreg){
        this.rd = rd;
        this.memory = memory;
        this.imm = imm;
        this.addreg = addreg;
        this.regs = regs;
    }
    public void execute(){
        regs.put(rd,memory[regs.get(addreg)+imm]);
    }

} class sw extends Instruction{
    private String rd;
    private Integer imm;
    private String addreg;
    private int[] memory;

    private HashMap<String,Integer> regs;
    public sw(HashMap<String,Integer> regs,int[] memory,String rd, Integer imm, String addreg){
        this.rd = rd;
        this.memory = memory;
        this.imm = imm;
        this.addreg = addreg;
        this.regs = regs;
    }
    public void execute(){

        memory[regs.get(addreg)+imm] = regs.get(rd);
    }

} class j extends Instruction{


    private Integer imm;
    private HashMap<String,Integer> regs;
    public j(HashMap<String,Integer> regs, Integer imm){


        this.imm = imm;
        this.regs = regs;
    }
    public void execute(){regs.put("pc",imm - 1);}

} class jr extends Instruction{
    private String rs;
    private HashMap<String,Integer> regs;
    public jr(HashMap<String,Integer> regs, String rs){
        this.rs = rs;
        this.regs = regs;
    }
    public void execute(){
        regs.put("pc",regs.get(rs) - 1);
    }

} class jal extends Instruction{
    private Integer imm;


    private HashMap<String,Integer> regs;
    public jal(HashMap<String,Integer> regs, Integer imm){


        this.imm = imm;
        this.regs = regs;
    }
    public void execute(){
        regs.put("ra",regs.get("pc")+1);
        regs.put("pc",imm - 1);
    }

}