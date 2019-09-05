#include <QApplication>
#include <QBoxLayout>
#include <QDebug>
#include <QLabel>
#include <QStyle>
#include "hello.h"

MyPushButton::MyPushButton(const QString &text)
    : QPushButton(text)
{
    setObjectName("mypushbutton");
    qDebug() << "My PushButton has been constructed";
}

class MyLabel : public QLabel
{
public:
    MyLabel(const QString &text) : QLabel(text) {
        setAlignment(Qt::AlignTop | Qt::AlignLeft);
    }
};

int make_window_internal(void) {
    int argc = 0;
    QApplication app(argc, NULL);
    QWidget window;


    // This PoC demonstrates how to achieve the same visual appearence on native and web
    // TODO1: Break down the primitives such that the widgets can be placed according to the kotlin model
    // TODO2: Add rudimentary styling capabilities and try to build a very simple UI in kotlin code

    QVBoxLayout vlayout;

    QHBoxLayout layout;

    vlayout.addLayout(&layout);

    MyLabel label("roses are red");
    layout.addWidget(&label);
    MyLabel label2("violets are blue");
    layout.addWidget(&label2);
    MyLabel label3("undefined is not a function");
    layout.addWidget(&label3);
    layout.addStretch();
    layout.setContentsMargins(0, 0, 0, 0);
    layout.setSpacing(0);

    vlayout.setContentsMargins(0, 0, 0, 0);
    vlayout.setSpacing(0);
    vlayout.addStretch();

    window.setStyleSheet("* { background-color: white; font-family: \"Helvetica, sans-serif\"; }");
    window.setLayout(&vlayout);
    window.resize(800, 600);
    window.show();
    return app.exec();
}
