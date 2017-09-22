# TagEditText
[![Build Status](https://travis-ci.org/RyPope/TagEditText.svg?branch=master)](https://travis-ci.org/RyPope/TagEditText)

### Features:

 * Tap to remove a tag.
 * Modifiable radius, text appearance, background color and padding.


### Usage
Add this in your root build.gradle at the end of repositories:

```xml
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency:

```xml
	dependencies {
		compile 'com.github.RyPope:TagEditText:1.0.3'
	}
```

```xml
    <com.ryanpope.tagedittext.TagEditText
        android:id="@+id/edit_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@style/TagEditText" />
```

```xml
    <style name="TagEditText">
        <item name="android:layout_margin">5dp</item>
        <item name="textBackgroundRadius">3dp</item>
        <item name="textBackgroundColor">@android:color/holo_orange_dark</item>
        <item name="textSize">30sp</item>
    </style>
```

### Attributes:

```xml
        <attr name="textSize" format="dimension" />
        <attr name="textColor" format="color" />
        <attr name="textBackgroundColor" format="color" />
        <attr name="textBackgroundRadius" format="dimension" />
        <attr name="textPaddingTop" format="dimension" />
        <attr name="textPaddingBottom" format="dimension" />
        <attr name="textPaddingRight" format="dimension" />
        <attr name="textPaddingLeft" format="dimension" />
 ```
 
 ### Screenshots
 
 <img src="https://raw.githubusercontent.com/RyPope/TagEditText/master/assets/round_tag_orange_example.png" width="250">
 
 <img src="https://raw.githubusercontent.com/RyPope/TagEditText/master/assets/square_tag_example.png" width="250">
  
 <img src="https://raw.githubusercontent.com/RyPope/TagEditText/master/assets/example_tag_deletion.gif" width="250">
