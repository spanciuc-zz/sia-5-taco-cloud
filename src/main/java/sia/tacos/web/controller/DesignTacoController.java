package sia.tacos.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import sia.tacos.domain.model.Ingredient;
import sia.tacos.domain.model.Ingredient.Type;
import sia.tacos.domain.model.Order;
import sia.tacos.domain.model.Taco;
import sia.tacos.domain.repository.IngredientRepository;
import sia.tacos.domain.repository.TacoRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepository;

	private final TacoRepository tacoRepository;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepository,
			TacoRepository tacoRepository) {
		super();
		this.ingredientRepository = ingredientRepository;
		this.tacoRepository = tacoRepository;
	}

	@ModelAttribute("order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute("taco")
	public Taco taco() {
		return new Taco();
	}

	@GetMapping
	public String showDesignForm(Model model) {
		appendIngredientsAttributes(model);
		model.addAttribute("taco", new Taco());
		return "design";

	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors,
			@ModelAttribute("order") Order order, Model model) {
		if (errors.hasErrors()) {
			appendIngredientsAttributes(model);
			return "design";
		}

		Taco savedTaco = tacoRepository.save(taco);
		order.addTaco(savedTaco);

		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients,
			Type type) {
		return ingredients.stream().filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}

	private void appendIngredientsAttributes(Model model) {

		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		ingredientRepository.findAll().forEach(ingredients::add);

		Type[] types = Type.values();

		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
					filterByType(ingredients, type));
		}
	}

}
