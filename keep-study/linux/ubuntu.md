

ubuntu 16 upgrade to 18
You're now ready to upgrade to With Ubuntu 18.04.1. You do this with the following steps:

Open the "Software & Updates" Setting in System Settings.
Select the 3rd Tab called "Updates".
Set the "Notify me of a new Ubuntu version" drop down menu to "For any new version" if you are using 17.10; set it to "For long-term support versions" if you are using 16.04 LTS.
Press Alt+F2 and type update-manager -c into the command box.
Update Manager should open up and tell you that Ubuntu 18.04 LTS is now available.
If not you can run /usr/lib/ubuntu-release-upgrader/check-new-release-gtk
Click Upgrade and follow the on-screen instructions.
The upgraded Linux is about one and a half gigabytes so be ready for a long wait.

You can also upgrade from the shell with the following commands:

$ sudo apt update

$ sudo apt-get -f install

$ sudo apt upgrade

This makes sure your Ubuntu is up to date. Next, follow this up with:

$ sudo apt dist-upgrade

This handles changing software dependencies with new versions of packages.

I then follow this up with:

$ sudo apt-get autoremove

This removes dependencies from uninstalled applications. You can do the same thing from the GUI desktop with the utility application Bleachbit. After that, it's time to get things ready for the big upgrade with:

$ sudo apt install update-manager-core

Finally:

$ sudo do-release-upgrade

This will start upgrading your system to 18.04. Along the way, Ubuntu will ask you several questions about how to handle the upgrade.

It will be worth it. Besides Ubuntu 18.04's advantages, the 18.04.1 release, which is what you'll be upgrading to, also comes with the 4.15 Linux kernel. In addition, it also incorporates many other performance and security improvements.

If you want you can stick with Ubuntu 16.04. Its next update, Ubuntu 16.04.5, will appear in early August. Canonical will continue to support Ubuntu 16.04 until April 2021.

参考资料：
https://www.zdnet.com/article/how-to-upgrade-from-ubuntu-linux-16-04-to-18-04/
