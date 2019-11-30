package com.yzhang.monsterhunterworldcompanion.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.adapters.AmmoListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.CoatingListAdapter;
import com.yzhang.monsterhunterworldcompanion.adapters.SharpnessColorAdapter;
import com.yzhang.monsterhunterworldcompanion.appdatabase.AppDataBase;
import com.yzhang.monsterhunterworldcompanion.appdatabase.weapons.Weapon;
import com.yzhang.monsterhunterworldcompanion.viewmodels.WeaponDetailViewModel;
import com.yzhang.monsterhunterworldcompanion.viewmodels.WeaponDetailViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class WeaponDetailFragment extends Fragment {

    //const
    private static final String LOG_TAG = WeaponDetailFragment.class.getSimpleName();
    public static final String WEAPON_DETAIL_BUNDLE_KEY = "weapon_detail_bundle";
    public static final String WEAPON_ID_KEY = "weapon_id";
    public static final String ACTION_POPULATE_WEAPON_DETAIL =
            "com.yzhang.monsterhunterworldcompanion.populateWeaponDetail";
    private static final String GREAT_SWORD = "great-sword";
    private static final String LONG_SWORD = "long-sword";
    private static final String SWORD_AND_SHEILD = "sword-and-shield";
    private static final String DUAL_BLADES = "dual-blades";
    private static final String HAMMER = "hammer";
    private static final String HUNTING_HORN = "hunting-horn";
    private static final String LANCE = "lance";
    private static final String GUN_LANCE = "gunlance";
    private static final String SWITCH_AXE = "switch-axe";
    private static final String CHARGE_BLADE = "charge-blade";
    private static final String INSECT_GLAIVE = "insect-glaive";
    private static final String LIGHT_BOWGUN = "light-bowgun";
    private static final String HEAVY_BOWGUN = "heavy-bowgun";
    private static final String BOW = "bow";

    //UI
    private static WeaponDetailFragment mInstance;
    private ScrollView mWeaponDetailUi;
    private TextView mEmptyView;

    private TextView mWeaponNameTv;
    private TextView mWeaponRarityTv;
    private ImageView mWeaponImageIv;

    private TextView mAttackTv;
    private TextView mAffinityTv;

    private LinearLayout mElementSection;
    private ImageView mElementIconIv;
    private TextView mElementDamageTv;

    private RelativeLayout mSharpnessSection;
    private RecyclerView mSharpnessColor;
    private SharpnessColorAdapter mColorAdapter;
    private RecyclerView mSharpnessColorFull;
    private SharpnessColorAdapter mColorAdapterFull;

    private LinearLayout mSlotSection;
    private List<ImageView> mWeaponSlots = new ArrayList<>();

    private LinearLayout mShellingSection;
    private TextView mShellingTv;

    private LinearLayout mPhialSection;
    private TextView mPhialTv;

    private LinearLayout mKinsectSection;
    private TextView mKinsectTv;

    private LinearLayout mDeviationSection;
    private TextView mDeviationTv;

    private RelativeLayout mAmmoSection;
    private RecyclerView mAmmoListRv;
    private AmmoListAdapter mAmmoListAdapter;

    private RelativeLayout mCoatingSection;
    private RecyclerView mCoatingListRv;
    private CoatingListAdapter mCoatingListAdapter;

    private WeaponDetailViewModelFactory mViewModelFactory;
    private WeaponDetailViewModel mViewModel;
    private AppDataBase mDb;
    private BroadcastReceiver mBroadcastReceiver;

    //val
    private int mWeaponId;

    public WeaponDetailFragment() {
        // Required empty constructor
    }

    /** Life cycle begin */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weapon_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mInstance = this;
        mDb = AppDataBase.getInstance(getContext());

        mWeaponDetailUi = view.findViewById(R.id.weapon_detail_ui);
        mWeaponDetailUi.setVisibility(View.GONE);
        mEmptyView = view.findViewById(R.id.tv_weapon_detail_empty_view);

        mWeaponNameTv = view.findViewById(R.id.tv_weapon_detail_name);
        mWeaponRarityTv = view.findViewById(R.id.tv_weapon_detail_rarity);
        mWeaponImageIv = view.findViewById(R.id.iv_weapon_large_icon);

        mAttackTv = view.findViewById(R.id.tv_weapon_attack);
        mAffinityTv = view.findViewById(R.id.tv_weapon_affinity);

        mElementSection = view.findViewById(R.id.weapon_element_section);
        mElementIconIv = view.findViewById(R.id.iv_weapon_element_damage_icon);
        mElementDamageTv = view.findViewById(R.id.tv_weapon_element_damage);

        mSharpnessSection = view.findViewById(R.id.weapon_sharpness_section);
        mSharpnessColor = view.findViewById(R.id.rv_sharpness_color);
        mSharpnessColor.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false));
        mColorAdapter = new SharpnessColorAdapter(getContext(), null);
        mSharpnessColor.setAdapter(mColorAdapter);
        mSharpnessColorFull = view.findViewById(R.id.rv_full_sharpness_color);
        mSharpnessColorFull.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, false));
        mColorAdapterFull = new SharpnessColorAdapter(getContext(), null);
        mSharpnessColorFull.setAdapter(mColorAdapterFull);


        mSlotSection = view.findViewById(R.id.weapon_slot_section);
        ImageView slot1 = view.findViewById(R.id.iv_slot_1);
        mWeaponSlots.add(slot1);
        ImageView slot2 = view.findViewById(R.id.iv_slot_2);
        mWeaponSlots.add(slot2);
        ImageView slot3 = view.findViewById(R.id.iv_slot_3);
        mWeaponSlots.add(slot3);

        mShellingSection = view.findViewById(R.id.weapon_shelling_section);
        mShellingTv = view.findViewById(R.id.tv_weapon_shelling);

        mPhialSection = view.findViewById(R.id.weapon_phial_type_section);
        mPhialTv = view.findViewById(R.id.tv_weapon_phial_type);

        mKinsectSection = view.findViewById(R.id.weapon_kinsect_section);
        mKinsectTv = view.findViewById(R.id.tv_weapon_kinsect_bonus);

        mDeviationSection = view.findViewById(R.id.weapon_deviation_section);
        mDeviationTv = view.findViewById(R.id.tv_weapon_deviation);

        mAmmoSection = view.findViewById(R.id.ammo_section);
        mAmmoListRv = view.findViewById(R.id.rv_ammo_list);
        mAmmoListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAmmoListAdapter = new AmmoListAdapter(getContext(), null);
        mAmmoListRv.setAdapter(mAmmoListAdapter);

        mCoatingSection = view.findViewById(R.id.weapon_coating_section);
        mCoatingListRv = view.findViewById(R.id.rv_coating_list);
        mCoatingListRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCoatingListAdapter = new CoatingListAdapter(getContext(), null);
        mCoatingListRv.setAdapter(mCoatingListAdapter);

        createBroadCastReceiver();

        if(savedInstanceState != null) {
            if(savedInstanceState.containsKey(WEAPON_ID_KEY)) {
                mWeaponId = savedInstanceState.getInt(WEAPON_ID_KEY);
                populateWeaponDetail(mWeaponId);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(WEAPON_ID_KEY, mWeaponId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(mBroadcastReceiver);
    }
    /** Life cycle end */

    /** Initiate and setup view model */
    private void populateWeaponDetail(int weaponId) {
        mEmptyView.setVisibility(View.GONE);
        mWeaponDetailUi.setVisibility(View.VISIBLE);
        if(mViewModel != null && mViewModel.getWeapon().hasObservers()) {
            mViewModel.getWeapon().removeObservers(this);
        }

        mViewModelFactory = new WeaponDetailViewModelFactory(mDb, weaponId);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory)
                .get(WeaponDetailViewModel.class);
        mViewModel.updateLiveData(mDb, weaponId);
        mViewModel.getWeapon().observe(this, new Observer<Weapon>() {
            @Override
            public void onChanged(Weapon weapon) {
                setAttributeViews(weapon.getType());
                mWeaponNameTv.setText(weapon.getName());
                mWeaponRarityTv.setText("Rare " + weapon.getRarity());
                if(weapon.getRarity() <= 12 && weapon.getRarity() >= 1) {
                    mWeaponRarityTv.setTextColor(getContext().getResources()
                            .getColor(getRarityColor(weapon.getRarity())));
                }
                if(weapon.getAssets() != null) {
                    Glide.with(getContext())
                            .load(weapon.getAssets().getImage())
                            .placeholder(getWeaponIconPlaceHolder(getWeaponTypeId(weapon.getType())))
                            .into(mWeaponImageIv);
                } else {
                    Glide.with(getContext())
                            .load(getWeaponIconPlaceHolder(getWeaponTypeId(weapon.getType())))
                            .into(mWeaponImageIv);
                }
                mAttackTv.setText(weapon.getAttack().getDisplay() + "");
                mAffinityTv.setText(weapon.getAttributes().getAffinity() + "%");
                if(weapon.getElements() == null || weapon.getElements().isEmpty()) {
                    mElementDamageTv.setText(getContext()
                            .getResources().getString(R.string.weapon_detail_element_none_text));
                    mElementIconIv.setVisibility(View.GONE);
                } else {
                    mElementIconIv.setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(getElementIcon(weapon.getElements().get(0).getType()))
                            .into(mElementIconIv);
                    if(weapon.getElements().get(0).getHidden()) {
                        mElementDamageTv.setText("(" + weapon.getElements().get(0).getDamage() + ")");
                    } else {
                        mElementDamageTv.setText(weapon.getElements().get(0).getDamage() + "");
                    }
                }
                if(weapon.getShelling() != null) {
                    mShellingTv.setText(weapon.getShelling().getType()
                                    + " Lv."
                                    + weapon.getShelling().getLevel());
                }
                if(weapon.getPhial() != null) {
                    mPhialTv.setText(weapon.getPhial().getType());
                }
                if(weapon.getBoostType() != null && !weapon.getBoostType().isEmpty()) {
                    mKinsectTv.setText(weapon.getBoostType());
                }
                if(weapon.getDeviation() != null && !weapon.getDeviation().isEmpty()) {
                    mDeviationTv.setText(weapon.getDeviation());
                }
                if(weapon.getDurability() != null && !weapon.getDurability().isEmpty()) {
                    mColorAdapter.updateDataSet(weapon.getDurability().get(0));
                }
                if(weapon.getDurability() != null && weapon.getDurability().size() > 5) {
                    mColorAdapterFull.updateDataSet(weapon.getDurability().get(5));
                }
                if(weapon.getAmmo() != null && !weapon.getAmmo().isEmpty()) {
                    mAmmoListAdapter.updateDataSet(weapon.getAmmo());
                }
                if(weapon.getCoatings() != null && !weapon.getCoatings().isEmpty()) {
                    mCoatingListAdapter.updateDataSet(weapon.getCoatings());
                }
                loadDecorSlotIcon(weapon);
            }
        });
    }

    /** Create broadcast receiver to populate weapon detail */
    private void createBroadCastReceiver() {
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(ACTION_POPULATE_WEAPON_DETAIL)) {
                    Bundle bundle = intent.getBundleExtra(WEAPON_DETAIL_BUNDLE_KEY);
                    if(bundle == null) {
                        return;
                    }
                    if(bundle.containsKey(WEAPON_ID_KEY)) {
                        mWeaponId = bundle.getInt(WEAPON_ID_KEY);
                        mInstance.populateWeaponDetail(mWeaponId);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_POPULATE_WEAPON_DETAIL);
        getActivity().registerReceiver(mBroadcastReceiver, filter);
    }

    /** Set and hide attributes based on weapon type */
    private void setAttributeViews(String weaponType) {
        switch (weaponType) {
            case GREAT_SWORD:
            case LONG_SWORD:
            case SWORD_AND_SHEILD:
            case DUAL_BLADES:
            case HUNTING_HORN:
            case HAMMER:
            case LANCE:
                mElementSection.setVisibility(View.VISIBLE);
                mSharpnessSection.setVisibility(View.VISIBLE);
                mShellingSection.setVisibility(View.GONE);
                mPhialSection.setVisibility(View.GONE);
                mKinsectSection.setVisibility(View.GONE);
                mDeviationSection.setVisibility(View.GONE);
                mAmmoSection.setVisibility(View.GONE);
                mCoatingSection.setVisibility(View.GONE);
                break;
            case GUN_LANCE:
                mElementSection.setVisibility(View.VISIBLE);
                mSharpnessSection.setVisibility(View.VISIBLE);
                mShellingSection.setVisibility(View.VISIBLE);
                mPhialSection.setVisibility(View.GONE);
                mKinsectSection.setVisibility(View.GONE);
                mDeviationSection.setVisibility(View.GONE);
                mAmmoSection.setVisibility(View.GONE);
                mCoatingSection.setVisibility(View.GONE);
                break;
            case SWITCH_AXE:
            case CHARGE_BLADE:
                mElementSection.setVisibility(View.VISIBLE);
                mSharpnessSection.setVisibility(View.VISIBLE);
                mShellingSection.setVisibility(View.GONE);
                mPhialSection.setVisibility(View.VISIBLE);
                mKinsectSection.setVisibility(View.GONE);
                mDeviationSection.setVisibility(View.GONE);
                mAmmoSection.setVisibility(View.GONE);
                mCoatingSection.setVisibility(View.GONE);
                break;
            case INSECT_GLAIVE:
                mElementSection.setVisibility(View.VISIBLE);
                mSharpnessSection.setVisibility(View.VISIBLE);
                mShellingSection.setVisibility(View.GONE);
                mPhialSection.setVisibility(View.GONE);
                mKinsectSection.setVisibility(View.VISIBLE);
                mDeviationSection.setVisibility(View.GONE);
                mAmmoSection.setVisibility(View.GONE);
                mCoatingSection.setVisibility(View.GONE);
                break;
            case LIGHT_BOWGUN:
            case HEAVY_BOWGUN:
                mElementSection.setVisibility(View.GONE);
                mSharpnessSection.setVisibility(View.GONE);
                mShellingSection.setVisibility(View.GONE);
                mPhialSection.setVisibility(View.GONE);
                mKinsectSection.setVisibility(View.GONE);
                mDeviationSection.setVisibility(View.VISIBLE);
                mAmmoSection.setVisibility(View.VISIBLE);
                mCoatingSection.setVisibility(View.GONE);
                break;
            case BOW:
                mElementSection.setVisibility(View.VISIBLE);
                mSharpnessSection.setVisibility(View.GONE);
                mShellingSection.setVisibility(View.GONE);
                mPhialSection.setVisibility(View.GONE);
                mKinsectSection.setVisibility(View.GONE);
                mDeviationSection.setVisibility(View.GONE);
                mAmmoSection.setVisibility(View.GONE);
                mCoatingSection.setVisibility(View.VISIBLE);
                break;
            default: break;
        }
    }

    /** Load decoration slot icon */
    private void loadDecorSlotIcon(Weapon weapon) {
        if(weapon.getSlots() == null || weapon.getSlots().isEmpty()) {
            for(int i = 0; i < mWeaponSlots.size(); i++) {
                mWeaponSlots.get(i).setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load(R.drawable.no_decor_slot_icon)
                        .into(mWeaponSlots.get(i));
            }
        } else {
            List<Weapon.Slot> slots = weapon.getSlots();
            int slotIndex = 0;
            int slotViewIndex = 0;
            while(slotViewIndex < mWeaponSlots.size() || slotIndex < slots.size()) {
                if(slotViewIndex < mWeaponSlots.size() && slotIndex < slots.size()) {
                    mWeaponSlots.get(slotViewIndex).setVisibility(View.VISIBLE);
                    Glide.with(getContext())
                            .load(getSlotIcon(slots.get(slotIndex).getRank()))
                            .into(mWeaponSlots.get(slotViewIndex));
                    slotIndex++;
                    slotViewIndex++;
                } else if (slotViewIndex < mWeaponSlots.size()) {
                    mWeaponSlots.get(slotViewIndex).setVisibility(View.GONE);
                    slotViewIndex++;
                } else {
                    break;
                }
            }
        }
    }

    /** Get element icon by type */
    private int getElementIcon(String elementType) {
        int resourceId = getContext().getResources()
                .getIdentifier(elementType, "drawable", getContext().getPackageName());
        return resourceId;
    }

    /** Get the decor slot icon by level */
    private int getSlotIcon(int level) {
        int resourceId = getResources()
                .getIdentifier("decorlv" + level, "drawable", getContext().getPackageName());
        return resourceId;
    }

    /** Get the rarity color */
    private int getRarityColor(int id) {
        int colorId = getResources()
                .getIdentifier("colorRare" + id, "color", getContext().getPackageName());
        return colorId;
    }

    /** Get weapon icon place holder by type id */
    private int getWeaponIconPlaceHolder(int typeId) {
        int resourceId = getResources().getIdentifier(
                "weapon" + typeId,
                "drawable",
                getContext().getPackageName());
        return resourceId;
    }

    /** Get weapon type id by type*/
    public static int getWeaponTypeId(String type) {
        switch (type) {
            case GREAT_SWORD:
                return 0;
            case LONG_SWORD:
                return 1;
            case SWORD_AND_SHEILD:
                return 2;
            case DUAL_BLADES:
                return 3;
            case HAMMER:
                return 4;
            case HUNTING_HORN:
                return 5;
            case LANCE:
                return 6;
            case GUN_LANCE:
                return 7;
            case SWITCH_AXE:
                return 8;
            case CHARGE_BLADE:
                return 9;
            case INSECT_GLAIVE:
                return 10;
            case LIGHT_BOWGUN:
                return 11;
            case HEAVY_BOWGUN:
                return 12;
            case BOW:
                return 13;
            default:break;
        }
        return 0;
    }

}
