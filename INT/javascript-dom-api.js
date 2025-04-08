/*
========================================================
DOM JavaScript API Notes
========================================================
*/

//================================ document

// Selecting elements
document.getElementById("foo");
document.getElementsByClassName("bar"); // Multiple elements
document.querySelector(".foo"); // First match
document.querySelectorAll(".foo"); // All matches

document.createElement("div");
document.createElement("p");

document.body.appendChild(div20);
document.body.insertBefore(div10, div20);
document.body.insertBefore(div30, div20.nextElementSibling);

//================================ el (Element Methods & Properties)

let el = document.getElementById("foo");

// Modifying Content
el.innerHTML = "<p>aaa</p>"; // Caution: Can execute scripts
el.textContent = "aaa"; // Safer
el.innerText = "aaa"; // Ignores CSS styling

// Attributes
el.setAttribute("id", "123");
el.getAttribute("id");
el.removeAttribute("id");

// Style
el.style.color = "blue";
el.style.maxHeight = "100px";

// Class List Manipulation
el.classList.add("foo");
el.classList.remove("active");
el.classList.toggle("active");
el.classList.contains("active");
el.classList.replace("foo", "bar");

// Traversing the DOM
el.parentElement;
el.children;
el.firstElementChild;
el.lastElementChild;
el.nextElementSibling;
el.previousElementSibling;

// Modifying the DOM
el.appendChild(child);
el.prepend(newChild);
el.remove();
el.removeChild(child);
el.replaceChild(newChild, oldChild);
el.before(beforeNode);
el.after(afterNode);

// Event Handling
el.addEventListener("click", callback);
el.removeEventListener("click", callback);
el.dispatchEvent(new Event("click")); // Manually trigger event

//================================ Node List (Multiple Elements)

document.querySelectorAll(".foo").forEach((el) => {
  el.addEventListener("click", () => {
    el.parentElement;
  });
});

//================================ Dataset API
el.dataset.foo;
el.dataset.foo = "bar";

//================================ Events

// Stopping Event Propagation
event.stopPropagation(); // Prevents bubbling
event.preventDefault(); // Prevents default action (if applicable)

//================================ Event Bubbling
/*
When clicking a deeply nested element, the click event propagates up to its ancestors.
Example:

┌───────────────┐
│               │
│  ┌─────────┐  │
│  │         │  │
│  │    ┌─┐  │  │
│  │    │X│  │  │ <- Click here, event bubbles up
│  │    └─┘  │  │
│  │         │  │
│  └─────────┘  │
│               │
└───────────────┘
*/

//================================ Common Events

// Mouse Events
"click", "dblclick";
"mouseup", "mousedown", "mouseenter";
"mouseout", "mouseover", "mousemove", "mouseleave";

// Keyboard Events
"keydown", "keyup";

// Form Events
"input", "submit", "focus", "blur", "reset", "change";

// Clipboard Events
"copy", "paste", "cut";

// Window Events
"load", "unload", "scroll", "resize", "hashchange";

// Drag Events
"dragstart", "drag", "dragenter", "dragover", "dragleave", "drop", "dragend";

// Miscellaneous Events
("wheel");
