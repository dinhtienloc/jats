//package vn.locdt.jats.bundle.question.util;
//
//import org.jline.keymap.KeyMap;
//import org.jline.reader.Binding;
//import org.jline.reader.Reference;
//import org.jline.utils.InfoCmp;
//
//public class KeyMapUtils {
//    private KeyMap<Binding> defaultKeyMaps() {
//        KeyMap<Binding> map = new KeyMap<>();
//        bind(map, );
//    }
//
//    private void bind(KeyMap<Binding> map, String widget, CharSequence... keySeqs) {
//        map.bind(new Reference(widget), keySeqs);
//    }
//
//    private void bindArrowKeys(KeyMap<Binding> map) {
//        bind(map, UP_LINE_OR_SEARCH,    key(InfoCmp.Capability.key_up));
//        bind(map, DOWN_LINE_OR_SEARCH,  key(InfoCmp.Capability.key_down));
//        bind(map, BACKWARD_CHAR,        key(InfoCmp.Capability.key_left));
//        bind(map, FORWARD_CHAR,         key(InfoCmp.Capability.key_right));
//        bind(map, BEGINNING_OF_LINE,    key(InfoCmp.Capability.key_home));
//        bind(map, END_OF_LINE,          key(InfoCmp.Capability.key_end));
//        bind(map, DELETE_CHAR,          key(InfoCmp.Capability.key_dc));
//        bind(map, KILL_WHOLE_LINE,      key(InfoCmp.Capability.key_dl));
//        bind(map, OVERWRITE_MODE,       key(InfoCmp.Capability.key_ic));
//        bind(map, MOUSE,                key(InfoCmp.Capability.key_mouse));
//        bind(map, BEGIN_PASTE,          BRACKETED_PASTE_BEGIN);
//    }
//}
