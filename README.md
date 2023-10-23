# Chaqupopy Jupyter launcher

This app runs a Jupyter notebook server. Although the on-screen URL says "localhost", it's
actually listening on all network interfaces.

A random 8-character password is generated and displayed on-screen. This will persist until the
app is uninstalled, so you can save it in your browser if you want.

Matplotlib is included: the `%matplotlib inline` and `%matplotlib notebook` modes both work.

The notebook runs with the following limitations:

* Only one kernel can run at a time. The app shows a status bar notification whenever the
  kernel is active. Before opening a different notebook, shut down the active one with the
  Kernel &gt; Shutdown command, or the "Running" tab in the main window. Merely closing the
  notebook in your browser is not enough.

* The Kernel &gt; Interrupt command does not work. Use Kernel &gt; Restart instead.
