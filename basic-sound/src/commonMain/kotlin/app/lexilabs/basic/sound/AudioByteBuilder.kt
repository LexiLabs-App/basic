package app.lexilabs.basic.sound

public interface AudioByteBuilder {
    public fun load(context: Any, localPath: String): Any
    public fun play(item: Any)
    public fun release()
}