import tkinter as tk
from tkinter import filedialog
from PIL import Image
import csv
import os

class ImageDividerApp:
    def __init__(self, master):
        self.master = master
        master.title("Image Divider")

        self.image_label = tk.Label(master, text="Aucune image sélectionnée")
        self.image_label.pack()

        self.select_button = tk.Button(master, text="Sélectionner une image", command=self.select_image)
        self.select_button.pack()
        self.image_path='';

        self.rows_label = tk.Label(master, text="Nombre de lignes:")
        self.rows_label.pack()
        self.rows_entry = tk.Entry(master)
        self.rows_entry.pack()

        self.columns_label = tk.Label(master, text="Nombre de colonnes:")
        self.columns_label.pack()
        self.columns_entry = tk.Entry(master)
        self.columns_entry.pack()

        self.divide_button = tk.Button(master, text="Diviser l'image", command=self.divide_image)
        self.divide_button.pack()

    def select_image(self):
        file_path = filedialog.askopenfilename()
        self.image_path=file_path
        if file_path:
            self.image = Image.open(file_path)
            self.image_label.config(text=f"Image sélectionnée: {file_path}")

    def divide_image(self):
        if hasattr(self, 'image'):
            try:
                rows = int(self.rows_entry.get())
                columns = int(self.columns_entry.get())

                width = self.image.width // columns
                height = self.image.height // rows

                pieces = []
                for row in range(rows):
                    for column in range(columns):
                        left = column * width
                        upper = row * height
                        right = (column + 1) * width
                        lower = (row + 1) * height

                        piece = self.image.crop((left, upper, right, lower))
                        pieces.append(piece)

                with open('image_info.csv', 'w', newline='') as csvfile:
                    writer = csv.writer(csvfile)
                    writer.writerow([self.image_path, rows, columns])
                    for i, piece in enumerate(pieces):
                        piece_path = f"pic/{i+1}.jpg"
                        piece.save(piece_path)

            except ValueError:
                self.image_label.config(text="Veuillez entrer des valeurs valides pour les lignes et les colonnes.")

        else:
            self.image_label.config(text="Veuillez sélectionner une image.")

root = tk.Tk()
app = ImageDividerApp(root)
root.mainloop()
