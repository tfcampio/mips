public class Memory {
    private byte[] data = new byte[256]; 
    public int readWord(int address) {
        return ((data[address] & 0xFF) << 24) | 
               ((data[address + 1] & 0xFF) << 16) | 
               ((data[address + 2] & 0xFF) << 8) | 
               (data[address + 3] & 0xFF);
    }
    public void writeWord(int address, int value) {
        data[address] = (byte) ((value >> 24) & 0xFF);
        data[address + 1] = (byte) ((value >> 16) & 0xFF);
        data[address + 2] = (byte) ((value >> 8) & 0xFF);
        data[address + 3] = (byte) (value & 0xFF);
    }
    public void loadProgram(int[] program) {
        for (int i = 0; i < program.length; i++) {
            writeWord(i * 4, program[i]);
        }
    }
}