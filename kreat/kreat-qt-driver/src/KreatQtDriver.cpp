#include "hello.h"

extern "C" void make_window(void);

void make_window(void) {
    make_window_internal();
}
