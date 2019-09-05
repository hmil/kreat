GW=./gradlew --stacktrace --info --console=plain
GW_KREAT=$(GW) -p kreat
GW_EXAMPLE=$(GW) -p examples/todomvc

NATIVE_DRIVER=libkreat-qt-driver.1.dylib
NATIVE_DRIVER_BUILD=kreat/kreat-qt-driver/libkreat-qt-driver.1.dylib
NATIVE_DRIVER_MAKEFILE=kreat/kreat-qt-driver/Makefile

.PHONY: build
build: $(NATIVE_DRIVER_BUILD)
	$(GW_KREAT) publishToMavenLocal

.PHONY: build-example-js
build-example-js: build
	$(GW_EXAMPLE) jsJar

.PHONY: run-js
run-js:
	cd examples/todomvc && npm start

$(NATIVE_DRIVER_MAKEFILE):
	cd kreat/kreat-qt-driver && qmake

.PHONY: $(NATIVE_DRIVER_BUILD)
$(NATIVE_DRIVER_BUILD): $(NATIVE_DRIVER_MAKEFILE)
	$(MAKE) -C kreat/kreat-qt-driver

examples/todomvc/$(NATIVE_DRIVER): $(NATIVE_DRIVER_BUILD)
	cp $(NATIVE_DRIVER_BUILD) ./examples/todomvc/

.PHONY: run-macos
run-macos: build examples/todomvc/$(NATIVE_DRIVER)
	$(GW_EXAMPLE) runTodomvcDebugExecutableMacos
