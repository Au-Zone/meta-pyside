## PySide layer for OpenEmbedded / Ångström Linux ##

This project merges a number of efforts to build PySide for OpenEmbedded. The focus of this project is to integrate with the Ångström distribution and build for the BeagleBone Black, but it should be portable across devices and distributions.

The project is tested with QT 4.8. As of this writing, the X11 version is tested working and the Embedded version is under development/testing.

### Additions for QT / QWS ###

In particular, this project adds the `QWSServer` and `QWSScreenSaver` classes to be able to hide the mouse cursor on touch screens, and
to enable custom screensavers with different levels.
