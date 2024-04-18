"use strict";

const sourceList = document.getElementById("source-list");
const targetList = document.getElementById("target-list");
const addItemInput = document.getElementById("add-item");
const addButton = document.getElementById("add-button");
const sourceCountSpan = document.getElementById("source-count");
const targetCountSpan = document.getElementById("target-count");

let draggElements = document.querySelectorAll(".draggable");

const allowDrop = event => {
  event.preventDefault();
};

const drag = event => {
  event.dataTransfer.setData("text/plain", event.target.innerText);
};

const drop = event => {
  event.preventDefault();
  const data = event.dataTransfer.getData("text/plain");
  const newItem = document.createElement("list");
  newItem.classList.add("draggable");
  newItem.setAttribute("draggable", "true");
  newItem.innerText = data;
  event.target.appendChild(newItem);

  // Remove from first list and frop on second
  if (event.target.parentNode.id === "target") {
    const sourceItem = sourceList.querySelector(`list:contains("${data}")`);
    if (sourceItem) {
      sourceList.removeChild(sourceItem);
    }
  }

  updateDragElements();
  updateCounts();
};

const updateDragElements = () => {
  draggElements = document.querySelectorAll(".draggable"); // Recache draggable elements
  draggElements.forEach(item => {
    item.addEventListener("dragstart", drag);
    item.addEventListener("click", handleEdit); // Add click event listener for editing
  });
};

const updateCounts = () => {
  sourceCountSpan.innerText = sourceList.childElementCount;
  targetCountSpan.innerText = targetList.childElementCount;
};

const addButtonHandler = () => {
  const newItemText = addItemInput.value.trim();
  if (newItemText) {
    const newItem = document.createElement("list");
    newItem.classList.add("draggable");
    newItem.setAttribute("draggable", "true");
    newItem.innerText = newItemText;
    sourceList.appendChild(newItem);
    addItemInput.value = ""; // Clear input field
    updateDragElements();
    updateCounts();
  }
};

const handleEdit = event => {
  const item = event.target;
  if (!item.classList.contains("editing")) {
    const originalText = item.innerText;
    const editInput = document.createElement("input");
    editInput.type = "text";
    editInput.value = originalText;
    item.classList.add("editing");
    item.innerHTML = "";
    item.appendChild(editInput);

    const editControls = document.createElement("div");
    editControls.classList.add("edit-controls");

    const saveButton = document.createElement("button");
    saveButton.innerText = "Save";
    saveButton.addEventListener("click", () => {
      const newText = editInput.value.trim();
      if (newText) {
        item.innerText = newText;
        item.classList.remove("editing");
      } else {
        item.innerText = originalText; // Restore original text if empty
        item.classList.remove("editing");
      }
    });

    const deleteButton = document.createElement("button");
    deleteButton.innerText = "Delete";
    deleteButton.addEventListener("click", () => {
      sourceList.removeChild(item);
      updateDragElements();
      updateCounts();
    });

    editControls.appendChild(saveButton);
    editControls.appendChild(deleteButton);
    item.appendChild(editControls);

    editInput.focus(); // Focus on the input field for editing
  }
};

addButton.addEventListener("click", addButtonHandler);
updateDragElements();
updateCounts();

// Event listeners for drag and drop
sourceList.addEventListener("dragover", allowDrop);
targetList.addEventListener("dragover", allowDrop);
sourceList.addEventListener("drop", drop);
targetList.addEventListener("drop", drop);
