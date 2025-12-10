public class MiniSimulator {
    public static void main(String[] args) {
        Memory ram = new Memory();
        CPU cpu = new CPU(ram);
        int[] program = {
            0x2008000A, 
            0x20090001, 
            0x01095020, 
            0xAC0A0000 
        };

        ram.loadProgram(program);
        
        System.out.println("starting");
        cpu.run(); 
    }
}