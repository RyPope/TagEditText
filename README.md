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
		compile 'com.github.RyPope:TagEditText:1.0'
	}
```

### Attributes:

```xml
   <attr name="textAppearance" format="reference" />
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
