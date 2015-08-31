package com.imoviesong.htmlconsole;

import java.util.Locale;

import com.google.android.gms.ads.*;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class examples extends Activity implements TextToSpeech.OnInitListener {
	int lvtap = 0;
	private TextToSpeech tts;
	TextView tvExam;
	EditText etExam, etSearch;
	ArrayAdapter<String> adapter;
	ListView lvExam;
	Button btss;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example);
		toggleFullscreen(true);
		final TabHost th = (TabHost) findViewById(R.id.thExample);
		th.setup();
		TabSpec ts = th.newTabSpec("tab1");
		ts.setContent(R.id.tab1);
		ts.setIndicator("Menu");
		th.addTab(ts);

		ts = th.newTabSpec("tab2");
		ts.setContent(R.id.tab2);
		ts.setIndicator("Overview");
		th.addTab(ts);

		ts = th.newTabSpec("tab3");
		ts.setContent(R.id.tab3);
		ts.setIndicator("Example");
		th.addTab(ts);

		lvExam = (ListView) findViewById(R.id.lvExamplelist);
		etExam = (EditText) findViewById(R.id.etExample);
		tvExam = (TextView) findViewById(R.id.tvExapmle);
		etSearch = (EditText) findViewById(R.id.etExampleSearch);
		btss = (Button) findViewById(R.id.bExamTSS);
		tts = new TextToSpeech(this, this);

		ActionBar actionbar = getActionBar();
		actionbar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#FF184C85")));
		String[] values = new String[] { "1 :  Introduction",
				"2 :  HTML Basic", "3 :  HTML Attributes",
				"4 :  HTML Headings", "5 :  HTML Text Formatting",
				"6 :  HTML Links", "7 :  HTML <head>",
				"8 :  HTML Styles - CSS", "9 :  HTML Images",
				"10 : HTML Tables", "11 : HTML Lists",
				"12 : HTML <div> and <span>", "13 : HTML Layouts",
				"14 : HTML Forms and Input", "15 : HTML Iframes",
				"16 : HTML Scripts", "17 : HTML Article", "18 : HTML Audio",
				"19 : HTML canvas", "20 : HTML footer", "21 : HTML header",
				"22 : HTML progress", "23 : HTML video" };

		// Look up the AdView as a resource and load a request.
		AdView adView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		lvExam.setAdapter(adapter);
		etExam.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				etExam.selectAll();
				return false;
			}
		});

		btss.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (btss.getText().toString().contains("Speak")) {
					speakOut();
					btss.setText("Stop");
				} else {
					speakEnd();
					btss.setText("Speak");
				}

			}

		});

		etSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				examples.this.adapter.getFilter().filter(arg0);
			}

		});

		lvExam.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				lvtap = 1;
				String tapAt = lvExam.getItemAtPosition(position).toString();
				String gotIt = tapAt.replaceAll(" : .*", "");

				int pos = Integer.parseInt(gotIt);

				switch (pos - 1) {
				case 0:
					tvExam.setText("•HTML is a language for describing web pages. \n •HTML stands for Hyper Text Markup Language \n •HTML is a markup language \n A markup language is a set of markup tags \n The tags describe document content \n •HTML documents contain HTML tags and plain text. \n HTML documents are also called web pages. \n\n * Example explained \n •The DOCTYPE declaration defines the document type. \n •The text between <html> and </html> describes the web page. \n •The text between <body> and </body> is the visible page content. \n •The text between <h1> and </h1> is displayed as a heading. \n •The text between <p> and </p> is displayed as a paragraph. \n\n * HTML Tags \n HTML markup tags are usually called HTML tags \n •HTML tags are keywords (tag names) surrounded by angle brackets like <html> \n •HTML tags normally come in pairs like <b> and </b> \n •The first tag in a pair is the start tag, the second tag is the end tag \n •The end tag is written like the start tag, with a forward slash before the tag name \n •Start and end tags are also called opening tags and closing tags \n '' <tagname>content</tagname> '' \n\n * HTML Elements \n 'HTML tags' and 'HTML elements' are often used to describe the same thing. \n But strictly speaking, an HTML element is everything between the start tag and the end tag, including the tags: \n HTML Element: \n '<p>This is a paragraph.</p>'");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <h1>My First Heading</h1> \n <p>My first paragraph.</p> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 1:
					tvExam.setText("* HTML Headings \n HTML headings are defined with the <h1> to <h6> tags. \n * HTML Paragraphs \n HTML paragraphs are defined with the <p> tag. \n * HTML Links \n HTML links are defined with the <a> tag. \n * HTML Images \n HTML images are defined with the <img> tag.");
					etExam.setText("<h1>This is a heading</h1> \n <h2>This is a heading</h2> \n <h3>This is a heading</h3> \n\n <p>This is a paragraph.</p> \n <p>This is another paragraph.</p> \n\n <a href=\"http://www.bing.com\">This is a link</a> \n\n <img src=\"bing.jpg\" alt=\"bing.com\" width=\"104\" height=\"142\" > ");
					th.setCurrentTab(1);
					break;
				case 2:
					tvExam.setText("HTML elements can have attributes \n Attributes provide additional information about an element \n Attributes are always specified in the start tag \n Attributes come in name/value pairs like: name=\"value\". \n * Tip: \n In some rare situations, when the attribute value itself contains quotes, it is necessary to use single quotes: name='John \"ShotGun\" Nelson' \n * HTML Attributes Reference \n A complete list of legal attributes for each HTML element is listed in our: HTML Tag Reference. \n Below is a list of some attributes that can be used on any HTML element: \n class 	: Specifies one or more classnames for an element (refers to a class in a style sheet) \n id 	: Specifies a unique id for an element \n style 	: Specifies an inline CSS style for an element \n title 	: Specifies extra information about an element (displayed as a tool tip)");
					etExam.setText("<a href=\"http://www.w3schools.com\">This is a link</a> ");
					th.setCurrentTab(1);
					break;
				case 3:
					tvExam.setText("Headings are important in HTML documents. \n * HTML Headings \n Headings are defined with the <h1> to <h6> tags. \n <h1> defines the most important heading. <h6> defines the least important heading. \n Headings Are Important \n Use HTML headings for headings only. Don't use headings to make text BIG or bold. \n Search engines use your headings to index the structure and content of your web pages. \n Since users may skim your pages by its headings, it is important to use headings to show the document structure. \n H1 headings should be used as main headings, followed by H2 headings, then the less important H3 headings, and so on. \n * HTML Lines \n The <hr> tag creates a horizontal line in an HTML page. \n The hr element can be used to separate content \n HTML Comments \n Comments can be inserted into the HTML code to make it more readable and understandable. Comments are ignored by the browser and are not displayed. \n Comments are written like this: <!-- This is a comment -->  \n HTML Tag Reference \n W3Schools' tag reference contains additional information about these tags and their attributes. \n You will learn more about HTML tags and attributes in the next chapters of this tutorial. \n\n <html> 	Defines an HTML document \n <body> 	Defines the document's body \n <h1> to <h6> 	Defines HTML headings \n <hr> 	Defines a horizontal line \n <!--> 	Defines a comment");
					etExam.setText("<h1>This is a heading</h1> \n <h2>This is a heading</h2> \n <h3>This is a heading</h3> \n\n <p>This is a paragraph.</p> \n <hr> \n <p>This is a paragraph.</p> \n <hr> \n <p>This is a paragraph.</p> \n <!-- This is a comment --> ");
					th.setCurrentTab(1);
					break;
				case 4:
					tvExam.setText("* HTML Text Formatting Tags \n <b> 	Defines bold text \n <em> 	Defines emphasized text  \n <i> 	Defines a part of text in an alternate voice or mood \n <small> 	Defines smaller text \n <strong> 	Defines important text \n <sub> 	Defines subscripted text \n <sup> 	Defines superscripted text \n <ins> 	Defines inserted text \n <del> 	Defines deleted text \n <mark> 	Defines marked/highlighted text \n * HTML 'Computer Output' Tags \n <code> 	Defines computer code text \n <kbd> 	Defines keyboard text  \n <samp> 	Defines sample computer code \n <var> 	Defines a variable \n <pre> 	Defines preformatted text \n * HTML Citations, Quotations, and Definition Tags \n <abbr> 	Defines an abbreviation or acronym \n <address> 	Defines contact information for the author/owner of a document \n <bdo> 	Defines the text direction \n <blockquote> 	Defines a section that is quoted from another source \n <q> 	Defines an inline (short) quotation \n <cite> 	Defines the title of a work \n <dfn> 	Defines a definition term");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <p><b>This text is bold</b></p> \n <p><strong>This text is strong</strong></p> \n <p><em>This text is emphasized</em></p> \n <p><i>This text is italic</i></p> \n <p><small>This text is small</small></p> \n <p>This is<sub> subscript</sub> and <sup>superscript</sup></p> \n </body> \n </html> \n <!DOCTYPE html> \n <html> \n <body> \n <code>Computer code</code> \n <br> \n <kbd>Keyboard input</kbd> \n <br> \n <samp>Sample text</samp> \n <br> \n <var>Computer variable</var> \n <br> \n <p><b>Note:</b> These tags are often used to display computer/programming code.</p> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 5:
					tvExam.setText("* HTML Hyperlinks (Links) \n The HTML <a> tag defines a hyperlink. \n A hyperlink (or link) is a word, group of words, or image that you can click on to jump to another document. \n When you move the cursor over a link in a Web page, the arrow will turn into a little hand. \n The most important attribute of the <a> element is the href attribute, which indicates the link's destination. \n By default, links will appear as follows in all browsers: \n •An unvisited link is underlined and blue \n •A visited link is underlined and purple \n •An active link is underlined and red \n * HTML Link Syntax \n The HTML code for a link is simple. It looks like this: \n <a href=\"url\">Link text</a> \n HTML Links - The target Attribute \n The target attribute specifies where to open the linked document. \n The example below will open the linked document in a new browser window or a new tab: \n Example \n <a href=\"http://www.bing.com/\"  target=\"_blank\">Visit bing!</a>");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <p> \n This is an email link: \n <a href=\"mailto:someone@example.com?Subject=Hello%20again\"  target=\"_top\"> \n Send Mail</a> \n </p> \n <p> \n <b>Note:</b> Spaces between words should be replaced by %20 to ensure that the browser will display the text properly. \n </p> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 6:
					tvExam.setText("* The HTML <head> Element \n The <head> element is a container for all the head elements. Elements inside <head> can include scripts, instruct the browser where to \"find style sheets, provide meta information, and more.\" \n The following tags can be added to the head section: <title>, <style>, <meta>, <link>, <script>, <noscript>, and <base>. \n The HTML <title> Element \n The <title> tag defines the title of the document. \n The <title> element is required in all HTML/XHTML documents. \n The <title> element: \n • defines a title in the browser toolbar \n • provides a title for the page when it is added to favorites \n • displays a title for the page in search-engine results \n The HTML <base> Element \n The <base> tag specifies the base URL/target for all relative URLs in a page: \n <head> \n <base href=\"http://www.bing.com/images/\"  target=\"_blank\"  > \n </head> \n * The HTML <link> Element \n The <link> tag defines the relationship between a document and an external resource. \n The <link> tag is most used to link to style sheets: \n <head> \n <link rel=\"stylesheet\"  type=\"text/css\"  href=\"mystyle.css\"  > \n </head> \n * The HTML <style> Element \n The <style> tag is used to define style information for an HTML document. \n Inside the <style> element you specify how HTML elements should render in a browser: \n <head> \n <style type=\"text/css\"  > \n body {background-color:yellow;} \n p {color:blue;} \n </style> \n </head> \n * The HTML <meta> Element \n Metadata is data (information) about data. \n The <meta> tag provides metadata about the HTML document. Metadata will not be displayed on the page, but will be machine parsable. \n Meta elements are typically used to specify page description, keywords, author of the document, last modified, and other metadata. \n The metadata can be used by browsers (how to display content or reload page), search engines (keywords), or other web services. \n <meta> tags always go inside the <head> element. \n examlpes \n <meta name=\"keywords\"  content=\"HTML, CSS, XML, XHTML, JavaScript\"  > \n * The HTML <script> Element \n The <script> tag is used to define a client-side script, such as a JavaScript.");
					etExam.setText("<!DOCTYPE html> \n <html> \n <head> \n <meta name=\"description\"  content=\"Free Web tutorials\"  > \n <meta name=\"keywords\"  content=\"HTML,CSS,XML,JavaScript\"  > \n <meta name=\"author\"  content=\"tej\"  > \n <meta charset=\"UTF-8\"  > \n </head> \n <body> \n <p>All meta information goes in the head section...</p> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 7:
					tvExam.setText("CSS (Cascading Style Sheets) is used to style HTML elements. \n * Styling HTML with CSS \n CSS was introduced together with HTML 4, to provide a better way to style HTML elements. \n CSS can be added to HTML in the following ways: \n • Inline - using the style attribute in HTML elements \n • Internal - using the <style> element in the <head> section \n • External - using an external CSS file \n The preferred way to add CSS to HTML, is to put CSS syntax in separate CSS files. \n * Inline Styles \n An inline style can be used if a unique style is to be applied to one single occurrence of an element. \n To use inline styles, use the style attribute in the relevant tag. The style attribute can contain any CSS property. The example below shows how to change the text color and the left margin of a paragraph: \n <p style=\"color:blue;margin-left:20px;\"  >This is a paragraph.</p> \n * Internal Style Sheet \n An internal style sheet can be used if one single document has a unique style. Internal styles are defined in the <head> section of an HTML page, by using the <style> tag, like this: \n <head> \n <style type=\"text/css\"  > \n body {background-color:yellow;} \n p {color:blue;} \n </style> \n </head>  \n * External Style Sheet \n An external style sheet is ideal when the style is applied to many pages. With an external style sheet, you can change the look of an entire Web site by changing one file. Each page must link to the style sheet using the <link> tag. The <link> tag goes inside the \n <head> section: \n <head> \n <link rel=\"stylesheet\"  type=\"text/css\"  href=\"mystyle.css\"  > \n </head>");
					etExam.setText("<!DOCTYPE html> \n <html> \n <head> \n <style type=\"text/css\"  > \n h1 {color:red;} \n h2 {color:blue;} \n p {color:green;} \n </style> \n </head> \n <body> \n <h1>All header 1 elements will be red</h1> \n <h2>All header 2 elements will be blue</h2> \n <p>All text in paragraphs will be green.</p> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 8:
					tvExam.setText("* HTML Images - The <img> Tag and the Src Attribute \n In HTML, images are defined with the <img> tag. \n The <img> tag is empty, which means that it contains attributes only, and has no closing tag. \n To display an image on a page, you need to use the src attribute. Src stands for 'source'. The value of the src attribute is the URL of the image you want to display. \n Syntax for defining an image: \n <img src=\"url\"  alt=\"some_text\"  > \n The URL points to the location where the image is stored. An image named 'boat.gif', located in the 'images' directory on 'www.bing.com' has the URL: http://www.bing.com/images/boat.gif. \n The browser displays the image where the <img> tag occurs in the document. If you put an image tag between two paragraphs, the browser shows the first paragraph, then the image, and then the second paragraph. \n * HTML Images - The Alt Attribute \n The required alt attribute specifies an alternate text for an image, if the image cannot be displayed. \n The value of the alt attribute is an author-defined text: \n <img src=\"smiley.gif\"  alt=\"Smiley face\"  > \n The alt attribute provides alternative information for an image if a user for some reason cannot view it (because of slow connection, an error in the src attribute, or if the user uses a screen reader). \n * HTML Images - Set Height and Width of an Image \n The height and width attributes are used to specify the height and width of an image. \n The attribute values are specified in pixels by default: \n <img src=\"smiley.gif\"  alt=\"Smiley face\"  width=\"42\"  height=\"42\"  > \n Tip: It is a good practice to specify both the height and width attributes for an image. If these attributes are set, the space required for the image is reserved when the page is loaded. However, without these attributes, the browser does not know the size of the image. The effect will be that the page layout will change during loading (while the images load).");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <p>An image from another folder:</p> \n <img src=\"/images/chrome.gif\"  alt=\"Google Chrome\"  width=\"33\"  height=\"32\"  ><p>An image from W3Schools:</p> \n <img src=\"http://www.w3schools.com/images/w3schools_green.jpg\"  alt=\"W3Schools.com\"  width=\"104\"  height=\"142\"  > \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 9:
					tvExam.setText("* HTML Tables \n Tables are defined with the <table> tag. \n A table is divided into rows (with the <tr> tag), and each row is divided into data cells (with the <td> tag). td stands for 'table data,' and holds the content of a data cell. A <td> tag can contain text, links, images, lists, forms, other tables, etc. \n * HTML Tables and the Border Attribute \n If you do not specify a border attribute, the table will be displayed without borders. Sometimes this can be useful, but most of the time, we want the borders to show. \n To display a table with borders, specify the border attribute: \n <table border=\"1\"  > \n <tr> \n <td>Row 1, cell 1</td> \n <td>Row 1, cell 2</td> \n </tr> \n </table> \n HTML Table Tags \n <table> 	Defines a table \n <th> 	Defines a header cell in a table \n <tr> 	Defines a row in a table \n <td> 	Defines a cell in a table \n <caption> 	Defines a table caption \n <colgroup> 	Specifies a group of one or more columns in a table for formatting \n <col> 	Specifies column properties for each column within a <colgroup> element \n <thead> 	Groups the header content in a table \n <tbody> 	Groups the body content in a table \n <tfoot> 	Groups the footer content in a table");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <p> \n Each table starts with a table tag.  \n Each table row starts with a tr tag. \n * Each table data starts with a td tag. \n </p> \n <h4>One column:</h4> \n <table border=\"1\"  > \n  <tr> \n   <td>100</td> \n  </tr> \n </table> \n <h4>One row and three columns:</h4> \n <table border=\"1\"  > \n <tr> \n   <td>100</td> \n   <td>200</td> \n   <td>300</td> \n </tr> \n </table> \n <h4>Two rows and three columns:</h4> \n <table border=\"1\"  > \n <tr> \n   <td>100</td> \n   <td>200</td> \n   <td>300</td> \n </tr> \n <tr> \n   <td>400</td> \n   <td>500</td> \n   <td>600</td> \n </tr> \n </table> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 10:
					tvExam.setText("* HTML Unordered Lists \n An unordered list starts with the <ul> tag. Each list item starts with the <li> tag. \n The list items are marked with bullets (typically small black circles). \n <ul> \n <li>Coffee</li> \n <li>Milk</li> \n </ul> \n HTML Ordered Lists \n An ordered list starts with the <ol> tag. Each list item starts with the <li> tag. \n The list items are marked with numbers. \n <ol> \n <li>Coffee</li> \n <li>Milk</li> \n </ol> \n HTML Description Lists \n A description list is a list of terms/names, with a description of each term/name. \n The <dl> tag defines a description list. \n The <dl> tag is used in conjunction with <dt> (defines terms/names) and <dd> (describes each term/name): \n <dl> \n <dt>Coffee</dt> \n <dd>- black hot drink</dd> \n <dt>Milk</dt> \n <dd>- white cold drink</dd> \n </dl>");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <h4>Numbered list:</h4> \n <ol> \n  <li>Apples</li> \n  <li>Bananas</li> \n  <li>Lemons</li> \n  <li>Oranges</li> \n </ol>   \n <h4>Letters list:</h4> \n <ol type=\"A\"  > \n  <li>Apples</li> \n  <li>Bananas</li> \n  <li>Lemons</li> \n  <li>Oranges</li> \n </ol>   \n <h4>Lowercase letters list:</h4> \n <ol type=\"a\"  > \n  <li>Apples</li> \n  <li>Bananas</li> \n  <li>Lemons</li> \n  <li>Oranges</li> \n </ol>   \n <h4>Roman numbers list:</h4> \n <ol type=\"I\"  > \n  <li>Apples</li> \n  <li>Bananas</li> \n  <li>Lemons</li> \n  <li>Oranges</li> \n </ol>   \n <h4>Lowercase Roman numbers list:</h4> \n <ol type=\"i\"  > \n  <li>Apples</li> \n  <li>Bananas</li> \n  <li>Lemons</li> \n  <li>Oranges</li> \n </ol>   \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 11:
					tvExam.setText("HTML elements can be grouped together with <div> and <span>. \n * HTML Block Elements \n Most HTML elements are defined as block level elements or as inline elements. \n Block level elements normally start (and end) with a new line when displayed in a browser. \n Examples: <h1>, <p>, <ul>, <table> \n * HTML Inline Elements \n Inline elements are normally displayed without starting a new line. \n Examples: <b>, <td>, <a>, <img> \n * The HTML <div> Element \n The HTML <div> element is a block level element that can be used as a container for grouping other HTML elements. \n  The <div> element has no special meaning. Except that, because it is a block level element, the browser will display a line break before and after it. \n When used together with CSS, the <div> element can be used to set style attributes to large blocks of content. \n Another common use of the <div> element, is for document layout. It replaces the 'old way' of defining layout using tables. Using <table> elements for layout is not the correct use of <table>. The purpose of the <table> element is to display tabular data. \n * The HTML <span> Element \n The HTML <span> element is an inline element that can be used as a container for text. \n The <span> element has no special meaning. \n When used together with CSS, the <span> element can be used to set style attributes to parts of the text.");
					etExam.setText("<div style=\"color:#0000FF\"  > \n   <h3>This is a heading</h3> \n   <p>This is a paragraph.</p> \n </div>  \n\n\n <p>My mother has <span style=\"color:blue\"  >blue</span> eyes.</p> ");
					th.setCurrentTab(1);
					break;
				case 12:
					tvExam.setText("Web page layout is very important to make your website look good. \n Design your webpage layout very carefully. \n * Website Layouts \n Most websites have put their content in multiple columns (formatted like a magazine or newspaper). \n Multiple columns are created by using <div> or <table> elements. CSS are used to position elements, or to create backgrounds or colorful look for the pages. \n * HTML Layouts - Using <div> Elements \n The div element is a block level element used for grouping HTML elements. \n * HTML Layouts - Using Tables \n A simple way of creating layouts is by using the HTML <table> tag. \n Multiple columns are created by using <div> or <table> elements. CSS are used to position elements, or to create backgrounds or colorful look for the pages.");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <div id=\"container\"  style=\"width:500px\"  > \n <div id=\"header\"  style=\"background-color:#FFA500;\"  > \n <h1 style=\"margin-bottom:0;\"  >Main Title of Web Page</h1></div> \n <div id=\"menu\"  style=\"background-color:#FFD700;height:200px;width:100px;float:left;\"  > \n <b>Menu</b><br> \n HTML<br> \n CSS<br> \n JavaScript</div> \n <div id=\"content\"  style=\"background-color:#EEEEEE;height:200px;width:400px;float:left;\"  > \n Content goes here</div> \n <div id=\"footer\"  style=\"background-color:#FFA500;clear:both;text-align:center;\"  > \n Copyright </div> \n </div> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 13:
					tvExam.setText("* HTML Forms \n HTML forms are used to pass data to a server. \n An HTML form can contain input elements like text fields, checkboxes, radio-buttons, submit buttons and more. A form can also contain select lists, textarea, fieldset, legend, and label elements. \n The <form> tag is used to create an HTML form: \n <form> \n . \n input elements \n . \n </form> \n * HTML Forms - The Input Element \n The most important form element is the <input> element. \n The <input> element is used to select user information. \n An <input> element can vary in many ways, depending on the type attribute. An <input> element can be of type text field, checkbox, password, radio button, submit button, and more. \n The most common input types are described below. \n * Text Fields \n <input type=\"text\"  > defines a one-line input field that a user can enter text into: \n <form> \n First name: <input type=\"text\"  name=\"firstname\"  ><br> \n Last name: <input type=\"text\"  name=\"lastname\"  > \n </form> \n Password Field \n <input type=\"password\"  > defines a password field: \n <form> \n Password: <input type=\"password\"  name=\"pwd\"  > \n </form> \n * Radio Buttons \n <input type=\"radio\"  > defines a radio button. Radio buttons let a user select ONLY ONE of a limited number of choices: \n <form> \n <input type=\"radio\"  name=\"sex\"   value=\"male\"  >Male<br> \n <input type=\"radio\"  name=\"sex\"   value=\"female\"  >Female \n </form> \n * Checkboxes \n <input type=\"checkbox\"  > defines a checkbox. Checkboxes let a user select ZERO or MORE options of a limited number of choices. \n <form> \n <input type=\"checkbox\"  name=\"vehicle\"   value=\"Bike\"  >I have a bike<br> \n <input type=\"checkbox\"   name=\"vehicle\"  value=\"Car\"  >I have a car \n </form>");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <form name=\"input\"   action=\"html_form_action.asp\"   method=\"get\"  > \n First name: <input type=\"text\"   name=\"FirstName\"   value=\"Mickey\"  ><br> \n Last name: <input type=\"text\"   name=\"LastName\"   value=\"Mouse\"  ><br> \n <input type=\"submit\"   value=\"Submit\"  > \n </form>  \n <p>If you click the 'submit' button, the form-data will be sent to a page called 'html_form_action.asp'.</p> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 14:
					tvExam.setText("An iframe is used to display a web page within a web page. \n Syntax for adding an iframe: \n <iframe src=\"URL\"  ></iframe> \n * Iframe - Set Height and Width \n The height and width attributes are used to specify the height and width of the iframe. \n The attribute values are specified in pixels by default, but they can also be in percent (like '80%'). \n Iframe - Remove the Border \n The frameborder attribute specifies whether or not to display a border around the iframe. \n Set the attribute value to '0' to remove the border:");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <iframe src=\"demo_iframe.htm\"  frameborder=\"0\"  ></iframe> \n <p>Some older browsers don't support iframes.</p> \n <p>If they don't, the iframe will not be visible.</p> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 15:
					tvExam.setText("JavaScripts make HTML pages more dynamic and interactive. \n * The HTML <script> Tag \n The <script> tag is used to define a client-side script, such as a JavaScript. \n The <script> element either contains scripting statements or it points to an external script file through the src attribute. \n Common uses for JavaScript are image manipulation, form validation, and dynamic changes of content. \n The script below writes Hello World! to the HTML output: \n Example \n <script> \n document.write(\"Hello World!\"  ) \n </script> \n * The HTML <noscript> Tag \n The <noscript> tag is used to provide an alternate content for users that have disabled scripts in their browser or have a browser that doesn't support client-side scripting. \n The <noscript> element can contain all the elements that you can find inside the <body> element of a normal HTML page. \n The content inside the <noscript> element will only be displayed if scripts are not supported, or are disabled in the user's browser: \n Example \n <script> \n document.write(\"Hello World!\"  ) \n </script> \n <noscript>Sorry, your browser does not support JavaScript!</noscript>");
					etExam.setText("<!DOCTYPE html> \n <html> \n <body> \n <h1>My First JavaScript</h1> \n <p id=\"demo\"  > \n JavaScript can react to events. Like the click of a button. \n </p> \n <script> \n function myFunction() \n { \n document.getElementById(\"demo\"  ).innerHTML=\"Hello JavaScript!\"  ; \n } \n </script> \n <button type=\"button\"   onclick=\"myFunction()\"  >Click Me!</button> \n </body> \n </html>");
					th.setCurrentTab(1);
					break;
				case 16:
					tvExam.setText(":The <article> tag specifies independent, self-contained content.\nAn article should make sense on its own and it should be possible to distribute it independently from the rest of the site.\nPotential sources for the <article> element:\nForum post.\nBlog post.\nNews story.\nComment.");
					etExam.setText("<!DOCTYPE html>/n<html>\n<body>\n<article>\n<h1>Google Chrome</h1>\n<p>Google Chrome is a free, open-source web browser developed by Google, released in 2008.</p>/n</article>\n</body>\n</html>");
					th.setCurrentTab(1);
					break;
				case 17:
					tvExam.setText("The <audio> tag defines sound, such as music or other audio streams.\nCurrently, there are 3 supported file formats for the <audio> element: MP3, Wav, and Ogg.\nAny text inside the between <audio> and </audio> will be displayed in browsers that do not support the <audio> tag.");
					etExam.setText("<!DOCTYPE html>\n<html>\n<body>\n<audio controls><source src=\"horse.ogg\" type=\"audio/ogg\">\n<source src=\"horse.mp3\" type=\"audio/mpeg\">\nYour browser does not support the audio element.\n</audio>\n</body>\n</html>");
					th.setCurrentTab(1);
					break;
				case 18:
					tvExam.setText("The <canvas> tag is used to draw graphics, on the fly, via scripting (usually JavaScript).\nThe <canvas> tag is only a container for graphics, you must use a script to actually draw the graphics.\nAny text inside the <canvas> element will be displayed in browsers that does not support <canvas>.");
					etExam.setText("<!DOCTYPE html>\n<html>\n<body>\n<canvas id=\"myCanvas\">\nYour browser does not support the HTML5 canvas tag.\n</canvas>\n<script>\nvar c=document.getElementById('myCanvas');\nvar ctx=c.getContext('2d');\nctx.fillStyle='#FF0000';\nctx.fillRect(0,0,80,100);\n</script>\n</body>\n</html>");
					th.setCurrentTab(1);
					break;
				case 19:
					tvExam.setText("The <footer> tag defines a footer for a document or section.\nA <footer> element should contain information about its containing element.\nA footer typically contains the author of the document, copyright information, links to terms of use, contact information, etc.\nYou can have several <footer> elements in one document.");
					etExam.setText("<footer>\n<p>Posted by: Hege Refsnes</p>\n<p>Contact information: <a href=\"mailto:someone@example.com\">\nsomeone@example.com</a>.</p>\n</footer>");
					th.setCurrentTab(1);
					break;
				case 20:
					tvExam.setText("The <header> tag specifies a header for a document or section.\nThe <header> element should be used as a container for introductory content or set of navigational links.\nYou can have several <header> elements in one document. \nNote: A <header> tag cannot be placed within a <footer>, <address> or another <header> element.");
					etExam.setText("<article>\n<header>\n<h1>Internet Explorer 9</h1>\n<p><time pubdate datetime=\"2011-03-15\"></time></p>\n</header>\n<p>Windows Internet Explorer 9 (abbreviated as IE9) was released to the  public on March 14, 2011 at 21:00 PDT.....</p>\n</article>");
					th.setCurrentTab(1);
					break;
				case 21:
					tvExam.setText("The <progress> tag represents the progress of a task.\nTip: Use the <progress> tag in conjunction with JavaScript to display the progress of a task.\nNote: The <progress> tag is not suitable for representing a gauge (e.g. disk space usage or relevance of a query result). To represent a gauge, use the <meter> tag instead.");
					etExam.setText("<progress value=\"22\" max=\"100\"></progress> ");
					th.setCurrentTab(1);
					break;
				case 22:
					tvExam.setText("The <video> tag specifies video, such as a movie clip or other video streams.\nCurrently, there are 3 supported video formats for the <video> element: MP4, WebM, and Ogg\nmP4 = MPEG 4 files with H264 video codec and AAC audio codec\nWebM = WebM files with VP8 video codec and Vorbis audio codec\nOgg = Ogg files with Theora video codec and Vorbis audio codec");
					etExam.setText("<video width=\"320\" height=\"240\" controls>\n<source src=\"movie.mp4\" type=\"video/mp4\">\n<source src=\"movie.ogg\" type=\"video/ogg\">\nYour browser does not support the video tag.\n</video>");
					th.setCurrentTab(1);
					break;
				}
			}

		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menuexample, menu);
		return true;

	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		EditText etExam = (EditText) findViewById(R.id.etExample);
		switch (item.getItemId()) {
		case R.id.copyExample:

			int sdk = android.os.Build.VERSION.SDK_INT;
			if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
				android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				clipboard.setText(etExam.getText().toString());
			} else {
				android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				android.content.ClipData clip = android.content.ClipData
						.newPlainText("example", etExam.getText().toString());
				clipboard.setPrimaryClip(clip);
			}
			if (lvtap == 0) {
				Context context = getApplicationContext();
				CharSequence text = "Please select one topic first.";
				int duration = Toast.LENGTH_LONG;
				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			} else {
				Context context = getApplicationContext();
				CharSequence text = "Example Copied To Clipboard";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				finish();
			}

			break;
		}
		return false;
	}

	public void toggleFullscreen(boolean fullscreen) {
		WindowManager.LayoutParams attrs = getWindow().getAttributes();
		if (fullscreen) {
			attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		} else {
			attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
		}
		getWindow().setAttributes(attrs);
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				btss.setEnabled(true);
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}

	private void speakOut() {

		String text = tvExam.getText().toString();
		text = text.replace("*", "");
		text = text.replace("<", "");
		text = text.replace(">", "");

		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

	private void speakEnd() {
		if (tts != null) {
			tts.stop();
		}
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

}
