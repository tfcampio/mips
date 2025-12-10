public class CPU {
    private int[] registers = new int[32]; 
    private int pc = 0; 
    private int ir = 0; 
    private Memory mem;

    public CPU(Memory memory) {
        this.mem = memory;
    }

    public void run() {
        while (pc < 256 && pc >= 0) { 
            fetch();
            decodeAndExecute();
        }
    }

    private void fetch() {
        ir = mem.readWord(pc);
        pc += 4;
    }

    private void decodeAndExecute() {
        int opcode = (ir >>> 26) & 0x3F; 
        int rs = (ir >>> 21) & 0x1F;
        int rt = (ir >>> 16) & 0x1F;
        int rd = (ir >>> 11) & 0x1F;
        int funct = ir & 0x3F;
        short immediate = (short) (ir & 0xFFFF);
        switch (opcode) {
            case 0x00: 
                handleRType(rs, rt, rd, funct);
                break;
            case 0x08:
                registers[rt] = registers[rs] + immediate;
                break;
            case 0x23:
                registers[rt] = mem.readWord(registers[rs] + immediate);
                break;
            case 0x2B: 
                mem.writeWord(registers[rs] + immediate, registers[rt]);
                break;
            case 0x04:
                if (registers[rs] == registers[rt]) {
                    pc += (immediate * 4) - 4; 
                }
                break;
            default:
        }
        registers[0] = 0;
    }

    private void handleRType(int rs, int rt, int rd, int funct) {
        switch (funct) {
            case 0x20:
                registers[rd] = registers[rs] + registers[rt];
                break;
            case 0x22:
                registers[rd] = registers[rs] - registers[rt];
                break;
        }
    }
}